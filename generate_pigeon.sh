#!/bin/bash

set -e

mkdir -p android/src/main/java/com/yandex/varioqub/flutter/pigeon
"${1:-dart}" run pigeon --input pigeons/varioqub_api.dart
