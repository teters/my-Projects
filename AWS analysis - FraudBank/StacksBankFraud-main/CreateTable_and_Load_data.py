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
    
    cursor.execute("""CREATE TABLE IF NOT EXISTS transactions (
        id INT PRIMARY KEY,
        trans_date_trans_time TIMESTAMP,
        cc_num BIGINT,
        merchant VARCHAR(255),
        category VARCHAR(255),
        amt DECIMAL(10,2),
        first_name VARCHAR(255),
        last_name VARCHAR(255),
        gender CHAR(1),
        street VARCHAR(255),
        city VARCHAR(255),
        state CHAR(2),
        zip INT,
        latitude DECIMAL(9,6),
        longitude DECIMAL(9,6),
        city_pop INT,
        job VARCHAR(255),
        dob DATE,
        trans_num VARCHAR(255),
        unix_time BIGINT,
        merch_lat DECIMAL(9,6),
        merch_long DECIMAL(9,6),
        is_fraud TINYINT
    )""")
    
    with open('/tmp/my-file', newline='') as csvfile:#abre el csv con nombre guardado en filename
        reader = csv.reader(csvfile, delimiter=',', quotechar='"')
        next(reader)
        i=0
        batch_size = 10000
        for row in reader:
            if len(row) == 23:
                sql = "INSERT INTO transactions (id,trans_date_trans_time,cc_num,merchant,category,amt,first_name,last_name,gender,street,city,state,zip,latitude,longitude,city_pop,job,dob,trans_num,unix_time,merch_lat,merch_long,is_fraud) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)"
                cursor.execute(sql, (row[0],row[1],row[2],row[3],row[4],row[5],row[6],row[7],row[8],row[9],row[10],row[11],row[12],row[13],row[14],row[15],row[16],row[17],row[18],row[19],row[20],row[21],row[22]))
                i += 1
                if i % batch_size == 0:
                    conn.commit()
            else:
                print(f"Row has unexpected number of columns: {len(row)}")
    
    conn.commit()  # make sure to commit any remaining changes

    cursor.close()
    conn.close()