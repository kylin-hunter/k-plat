package io.github.kylinhunter.plat.data.kafka;

import java.util.Collection;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * @author BiJi'an
 * @description
 * @date 2023-11-12 16:56
 */
@Component
@RequiredArgsConstructor
public class KafkaListenerManager {

  private final KafkaListenerEndpointRegistry registry;

  /**
   * @param id id
   * @return boolean
   * @title pause
   * @description pause
   * @author BiJi'an
   * @date 2023-11-12 17:01
   */
  public boolean pause(String id) {
    MessageListenerContainer listenerContainer = registry.getListenerContainer(id);
    Objects.requireNonNull(listenerContainer);
    listenerContainer.pause();
    return true;
  }

  /**
   * @return boolean
   * @title pauseAll
   * @description pauseAll
   * @author BiJi'an
   * @date 2023-11-12 17:03
   */
  public boolean pauseAll() {
    Collection<MessageListenerContainer> listeners = registry.getAllListenerContainers();
    listeners.forEach(MessageListenerContainer::pause);
    return true;
  }


  /**
   * @param id id
   * @return boolean
   * @title resume
   * @description resume
   * @author BiJi'an
   * @date 2023-11-12 17:01
   */

  public boolean resume(String id) {
    MessageListenerContainer listenerContainer = registry.getListenerContainer(id);
    Objects.requireNonNull(listenerContainer);
    listenerContainer.resume();
    return true;
  }

  /**
   * @return boolean
   * @title resumeAll
   * @description resumeAll
   * @author BiJi'an
   * @date 2023-11-12 17:04
   */
  public boolean resumeAll() {
    Collection<MessageListenerContainer> listeners = registry.getAllListenerContainers();
    listeners.forEach(MessageListenerContainer::resume);
    return true;
  }


  /**
   * @param id id
   * @return boolean
   * @title start
   * @description start
   * @author BiJi'an
   * @date 2023-11-12 17:01
   */

  public boolean start(String id) {
    MessageListenerContainer listenerContainer = registry.getListenerContainer(id);
    Objects.requireNonNull(listenerContainer);

    listenerContainer.start();
    return true;
  }

  public boolean startAll() {
    Collection<MessageListenerContainer> listeners = registry.getAllListenerContainers();
    listeners.forEach(MessageListenerContainer::start);
    return true;
  }

  /**
   * @param id id
   * @return boolean
   * @title stop
   * @description stop
   * @author BiJi'an
   * @date 2023-11-12 17:01
   */

  public boolean stop(String id) {
    MessageListenerContainer listenerContainer = registry.getListenerContainer(id);
    Objects.requireNonNull(listenerContainer);

    listenerContainer.stop();
    return true;
  }

  public boolean stopAll() {
    Collection<MessageListenerContainer> listeners = registry.getAllListenerContainers();
    listeners.forEach(MessageListenerContainer::stop);
    return true;
  }
}