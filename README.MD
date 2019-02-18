### How to run this project against local dynamodb and notes about dynamodb

Install the local dynamodb docker image:

`https://hub.docker.com/r/amazon/dynamodb-local`

Make sure that docker is running and then run the command:

`docker run -p 8000:8000 amazon/dynamodb-local`

See the dynamodb cli documentation here:

`https://docs.aws.amazon.com/cli/latest/reference/dynamodb/index.html`

See this SO post for how to make a table:

`https://stackoverflow.com/questions/42435407/create-dynamodb-table-using-aws-cli`

##Some CLI commands:

####List tables: 
`aws dynamodb list-tables --endpoint-url http://localhost:8000`

####Create table (Use a json file to define table):

`aws dynamodb create-table --cli-input-json file://create-table-movies.json  --endpoint-url http://localhost:8000`

####Delete table

`aws dynamodb delete-table --table-name Property --endpoint-url http://localhost:8000`
