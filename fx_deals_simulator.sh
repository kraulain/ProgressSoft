#!/bin/bash

# Database connection details
DB_HOST="localhost"
DB_NAME="sourcedb"
DB_USER="rootusername"
export DB_PASSWORD="rootpassword"

# Create table fxdeal
psql -h "$DB_HOST" -d "$DB_NAME" -U "$DB_USER" -W -c "
CREATE TABLE IF NOT EXISTS fxdeal (
  id serial PRIMARY KEY,
  from_currency varchar(255) NOT NULL,
  to_currency varchar(255) NOT NULL,
  created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  amount bigint NOT NULL
);
"

# Check for successful table creation
if [ $? -eq 0 ]; then
  echo "Table 'fxdeal' created successfully!"
else
  echo "Error creating table: $!"
  exit 1
fi

# Configure table for debezium
psql -h "$DB_HOST" -d "$DB_NAME" -U "$DB_USER" -W -c "
ALTER TABLE fxdeal REPLICA IDENTITY FULL;
"

# Check for successful configuration
if [ $? -eq 0 ]; then
  echo "Table 'fxdeal' configured for debezium!"
else
  echo "Error configuring table: $!"
  exit 1
fi

#setup up debezium config for table
curl -i -X POST -H "Accept:application/json" -H "Content-Type:application/json" 127.0.0.1:8083/connectors/ --data "@debezium.json"

# Define possible values for random data generation
FROM_CURRENCIES=('USD' 'EUR' 'GBP' 'JPY')
TO_CURRENCIES=('EUR' 'GBP' 'JPY' 'USD')
MIN_AMOUNT=100
MAX_AMOUNT=10000

# Main loop for continuous insertions
while true; do
  # Generate random values
  random_from_currency=${FROM_CURRENCIES[$RANDOM % ${#FROM_CURRENCIES[@]}]}
  random_to_currency=${TO_CURRENCIES[$RANDOM % ${#TO_CURRENCIES[@]}]}
  random_amount=$((RANDOM % ($MAX_AMOUNT - $MIN_AMOUNT + 1) + $MIN_AMOUNT))

  # Construct and execute the SQL insert statement
  sql="INSERT INTO fxdeal (from_currency, to_currency, amount) VALUES ('$random_from_currency', '$random_to_currency', $random_amount);"
  psql -h "$DB_HOST" -d "$DB_NAME" -U "$DB_USER" -W -c "$sql"

  # Check for successful insertion
  if [ $? -eq 0 ]; then
    echo "Inserted random data at $(date +"%H:%M:%S")"
  else
    echo "Error inserting data: $!"
  fi

  # Sleep for one second before next insertion
  sleep 1
done

echo "Script terminated."