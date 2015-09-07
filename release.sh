#!/bin/bash

VERSION=$1
BUILD=$2

echo "Creating release $VERSION.$BUILD"

git checkout -b release/$VERSION.$BUILD &&
echo "$VERSION.$BUILD" > .version && \
git add .version &&
git commit -am "Release version $VERSION.$BUILD" && \
git checkout master &&
git pull &&
git merge --no-ff release/$VERSION.$BUILD -m "Merged release/$VERSION.$BUILD into master" && \
git tag -a $VERSION.$BUILD -m "$VERSION.$BUILD" && \
git checkout develop && \
git merge --no-ff release/$VERSION.$BUILD  -m "Merged release/$VERSION.$BUILD into develop" && \
git branch -d release/$VERSION.$BUILD && \
echo "Release $VERSION.$BUILD created"
git pull && \
git push --all && git push origin $VERSION.$BUILD && \
echo "Release pushed to the repository"
