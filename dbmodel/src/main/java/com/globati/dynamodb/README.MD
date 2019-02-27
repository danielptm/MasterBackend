### How to run this project against local dynamodb and notes about dynamodb

####Install the local dynamodb docker image:
`https://hub.docker.com/r/amazon/dynamodb-local`

####Make sure that docker is running and then run the command:
`docker run -p 8000:8000 amazon/dynamodb-local`

####General dynamodb best practices
`https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/best-practices.html`

####Dynamodb cli documentation here:
`https://docs.aws.amazon.com/cli/latest/reference/dynamodb/index.html`

####SO post for how to make a table:
`https://stackoverflow.com/questions/42435407/create-dynamodb-table-using-aws-cli`

####Attribute data types:
`https://stackoverflow.com/questions/42435407/create-dynamodb-table-using-aws-cli `

####Dynamodb mapper with java
`https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBMapper.html`

####Dynamodb actions
`https://docs.aws.amazon.com/amazondynamodb/latest/APIReference/API_Operations.html`

##Some CLI commands:

###Start in shared db mode
`docker run -p 8000:8000 -v /Users/danieltuttle/dynamodblocal:/data/ amazon/dynamodb-local -jar DynamoDBLocal.jar -sharedDb -dbPath /data`

####List tables: 
`aws dynamodb list-tables --endpoint-url http://localhost:8000`

####Create table (Use a json file to define table):

`aws dynamodb create-table --cli-input-json file:///Users/danieltuttle/projects/globati/code/backend/MasterBackend/dbmodel/src/main/resources/dynamodb/CreateTable.json  --endpoint-url http://localhost:8000`

####Delete table

`aws dynamodb delete-table --table-name Property --endpoint-url http://localhost:8000`

####How to work with complex data types
`https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBMapper.ArbitraryDataMapping.html`

####Easier local testing with dynamodb SO post
`https://stackoverflow.com/questions/26901613/easier-dynamodb-local-testing`









