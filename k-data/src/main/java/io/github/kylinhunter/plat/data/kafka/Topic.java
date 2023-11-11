package io.github.kylinhunter.plat.data.kafka;

import lombok.Data;

/**
 * @author BiJi'an
 * @description
 * @date 2023-11-12 01:15
 */
@Data
public class Topic {
  private  String name;
  private  int  numPartitions;
  private  short replicationFactor;

}