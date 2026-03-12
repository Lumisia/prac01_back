package com.example.demo.likes;

import com.example.demo.board.BoardRepository;
import com.example.demo.board.model.Board;
import com.example.demo.likes.model.Likes;
import com.example.demo.user.model.AuthUserDetails;
import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikesService {
    private final LikesRepository likesRepository;
    private final BoardRepository boardRepository;

    // 트랜잭션 : 하나의 작업 단위를 묶어서 처리하는 것
    //      트랜잭션 -> ACID
    //          Atomicity : 트랜잭션이 모두 수행되거나 수행이 안되거나
    //          Consistency : 트랜잭션의 작업전과 작업후 데이터의 상태는 일관되어야한다.
    //          Isolation : 동시에 실행되는 트랜잭션은 서로 영향을 주면 안된다.
    //          Durability : 트랜잭션이 수행되고나서 데이터는 영구적으로 보관해야한다.

    // 동시성 제어가 안될 때 (격리성이 지켜지지 않았을 때) 발생하는 문제
    //      1. Dirty Read : 아직 커밋되지 않은 데이터를 다른 트랜잭션이 읽는 문제
    //      2. Non-Repeatable Read : 같은 트랜잭션 내에서 같은 데이터를
    //                      두번 읽었는데 값이 다른 문제
    //      3. Phantom Read : 같은 조건으로 두 번 조회했는데
    //                      결과 행의 수가 다른 문제

    // 격리 수준         Dirty Read      Non-Repeatable Read    Phantom Read    성능
    // READ UNCOMMITTED     발생              발생                  발생         빠름
    // READ COMMITTED       방치              발생                  발생
    // REPEATABLE READ      방치              방치                  발생
    // SERIALIZABLE         방치              방치                  방치         느림

    // 스프링의 Transactional와 DB의 트랜잭션은 비슷하지만 적용되는 레벨이 다름
    //      스프링의 Transactional 어플리케이션에서만 적용
    //      DB의 트랜잭션은 DB에서만 적용

    // 결국 실무에서는 락, 또는 쿼리를 직접 사용
    // Lock 종류
    //      비관적 락 : 최악의 상황을 생각해서 무조건 동시성 문제가
    //       (젤 중요한 애)       발생할 것이라고 가정하고 설정하는 방법
    //                  => 충돌이 발생하지 않게 사전에 방지
    //      낙관적 락 : 최선의 상황을 생각해서 문제가 발생하지 않을 수도
    //       (덜 중요한 애)       있지 않을까하고 가정하고 설정하는 방법
    //                  => 충돌이 생기면 예외를 발생 -> 해당 작업을 다시 실행

    // 락을 사용하지 않고 동시성 문제
    //      단순 카운트
    //
    @Transactional
    public /* synchronized */ void like(AuthUserDetails user, Long boardIdx) {
        Board board = boardRepository.findByIdx(boardIdx).orElseThrow(
                () -> {
                    System.out.printf("동시성 에러");
                    throw new OptimisticLockException();
                }
        );
        Likes likes = Likes.builder()
                .user(user.toEntity())
                .board(board)
                .build();
        likes = likesRepository.save(likes);
        board.increaseLikesCount();
        boardRepository.save(board);
    }
}
