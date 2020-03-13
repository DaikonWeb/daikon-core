#!/usr/bin/env sh
TAG=$1

git commit -am "Release ${TAG}"
git tag $TAG
git push
git push --tags
