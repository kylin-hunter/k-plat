package io.github.kylinhunter.plat.search.controller;

import io.github.kylinhunter.plat.data.kafka.KafkaListenerManager;
import io.github.kylinhunter.plat.search.msg.MsgSender;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author BiJi'an
 * @description
 * @date 2023-11-09 01:15
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/kafka")

public class KafkaController {

  private final MsgSender msgSender;

  private final KafkaListenerManager kafkaListenerManager;

  @PostMapping("/send")
  public boolean send(@RequestParam("message") String message) {
    msgSender.send(message);
    return true;
  }

  @PostMapping("/listener/pause_all")
  public boolean pause() {
    kafkaListenerManager.pauseAll();
    return true;
  }

  @PostMapping("/listener/resume_all")
  public boolean resume() {
    kafkaListenerManager.resumeAll();
    return true;
  }

  @PostMapping("/listener/start_all")
  public boolean start() {
    kafkaListenerManager.startAll();
    return true;
  }

  @PostMapping("/listener/stop_all")
  public boolean stop() {
    kafkaListenerManager.stopAll();
    return true;
  }

}