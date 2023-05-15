package com.sideproject.fikabackend.global.sse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@Component
public class SseEmitters {

    /**
     * 별도 스레드에서 실행되기 때문에 thread-safe 한 자료구조인 CopyOnWriteArrayList 를 사용
     * <pre>
     *     사용하지 않는 경우, ConcurrentModificationException 이 발생할 수 있다.
     * </pre>
     */
    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    /**
     * Emitter 생성 시 기존의 Emitter 객체 제거
     * <pre>
     *     SSE 타임아웃 발생 시 브라우저에서 재연결 요청을 보내면 Emitter 객체를 새로 생성하기 때문에
     *     기존의 Emitter 객체를 삭제해주어야 한다.
     *     이때 onCompletion 이라는 콜백 메서드를 사용한다.
     * </pre>
     * @param emitter 새로 생성할 emitter 객체
     * @return 새로 생성된 SseEmitter 객체
     */
    SseEmitter add(SseEmitter emitter) {
        this.emitters.add(emitter);
        log.info("new emitter added: {}", emitter);
        log.info("emitter list size: {}", emitters.size());
        emitter.onCompletion(() -> {
            log.info("onCompletion callback");
            this.emitters.remove(emitter);    // 만료되면 리스트에서 삭제
        });
        emitter.onTimeout(() -> {
            log.info("onTimeout callback");
            emitter.complete();
        });

        return emitter;
    }
}
