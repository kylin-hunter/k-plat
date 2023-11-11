package io.github.kylinhunter.plat.search.controller;

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
@RequestMapping("/msg")

public class MsgController {

  private final MsgSender msgSender;

  @PostMapping("/send")
  public void send(@RequestParam("message") String message) {
    msgSender.send(message);
  }

}