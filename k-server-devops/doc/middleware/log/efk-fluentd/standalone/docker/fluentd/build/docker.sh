#!/bin/bash
docker build -t kplat/k-fluentd:v1.16-1  .
docker rmi  kplat/k-fluentd:v1.16-1