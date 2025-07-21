#!/bin/bash
set -xe
TOOLDIR=$(dirname $0)
mkdir -p target/generated-site/markdown target/generated-site/resources/download
cp README target/generated-site/markdown/index.md
cp target/products/*.* target/generated-site/resources/download
