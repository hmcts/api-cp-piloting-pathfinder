
jar tvf ./build/libs/*.jar | grep "bom.json" >/dev/null
if [[ $? -eq 0 ]]
then
    echo "Found bom.json in jarfile"
    exit 0
else
    echo "Failed to find bom.json in jarfile"
    exit 1
fi
