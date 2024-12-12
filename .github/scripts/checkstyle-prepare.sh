#!/usr/bin/env bash

set -euxo pipefail

echo 'attempting to copy checkstyle-config.xml into super linter directory'

array=()

readarray -d '' array < <(sudo find /var/lib/docker/overlay2/ -type d -name '.automation' -print0)

len=${#array[*]}

i=0

while [ $i -lt "$len" ]; do
	sudo cp "${GITHUB_WORKSPACE}"/checkstyle_config.xml "${array[$i]}"
	((i++)) || true
done
