bomfile="bom.json"
jar tvf ./build/libs/*.jar | grep "$bomfile" >/dev/null
if [[ $? -eq 0 ]]
then
    echo "Found bom.json in jarfile"
    exit 0
else
    jar tvf ./build/libs/*.jar
    echo "Failed to find $bomfile in jarfile"
    exit 1
fi
