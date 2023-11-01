#!/bin/bash
docker build -t kplat/k-fluentd:v1.0.1  .
docker rmi  kplat/k-fluentd:v1.0.1