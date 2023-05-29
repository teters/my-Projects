import boto3
import csv
import pymysql
import os
import sys

def lambda_handler(event, context):
    s3 = boto3.client('s3')
    bucket_name = 'mybucketpythonborch'
    key = 'fraudTest.csv'
    file_name = '/tmp/my-file'
    s3.download_file(bucket_name, key, file_name)
    
    host = 'instance-bank-fraud.cbshv57jm3e0.us-east-1.rds.amazonaws.com'
    port = 3306
    user = 'borchDB'
    password = 'passwordDB'
    database = 'BankFraudDB'
    
    conn = pymysql.connect(host=host, port=port, user=user, password=password, database=database)
    cursor=conn.cursor()
    
    last_processed_id = 309999  # The last processed ID
    batch_size = 10000

    with open('/tmp/my-file', newline='') as csvfile:  # open the csv with the name saved in filename
        reader = csv.reader(csvfile, delimiter=',', quotechar='"')
        next(reader)
        i=0
        for row in reader:
            if len(row) == 23:
                id = int(row[0])
                if id > last_processed_id:
                    sql = "INSERT INTO transactions (id,trans_date_trans_time,cc_num,merchant,category,amt,first_name,last_name,gender,street,city,state,zip,latitude,longitude,city_pop,job,dob,trans_num,unix_time,merch_lat,merch_long,is_fraud) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)"
                    cursor.execute(sql, (row[0],row[1],row[2],row[3],row[4],row[5],row[6],row[7],row[8],row[9],row[10],row[11],row[12],row[13],row[14],row[15],row[16],row[17],row[18],row[19],row[20],row[21],row[22]))
                    i += 1
                    if i % batch_size == 0:
                        conn.commit()
                else:
                    continue
            else:
                print(f"Row has unexpected number of columns: {len(row)}")
    
    conn.commit()  # make sure to commit any remaining changes

    cursor.close()
    conn.close()
