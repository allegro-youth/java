
curl -X POST -d '{ "polish":"1", "english": "2", "math":"2", "history":"5" }' -H "Content-Type:application/json" http://localhost:8080/lessons | python -m json.tool