# Script to check that bom file has been created by coclonedx and added to jarfile
# Interim solution, it would be better to check the actual jarfile that has been pushed to repo
bomfile="bom.json"
jar tvf ./build/libs/*.jar | grep "$bomfile" >/dev/null
if [[ $? -eq 0 ]]
then
    echo "Found bom.json in jarfile"
    exit 0
else
    ls -l ./build/libs/*.jar
    jar tvf ./build/libs/*.jar
    echo "Failed to find $bomfile in jarfile"
    exit 1
fi
