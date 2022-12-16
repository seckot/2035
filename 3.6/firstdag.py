from datetime import datetime
from airflow import DAG
from airflow.operators.bash import BashOperator
from airflow.operators.python import PythonOperator
from random import randint

def hello():
    print("airflow")

def random_print():
    print(randint(0, 10))
    print(randint(0, 20))

def random_print_file():
    for _ in range(2):
        number_a = randint(1, 10)
        number_b = randint(1, 10)
        with open('/opt/airflow/dags/file.txt', 'a', newline='\n') as file:
            file.write(f'{number_a} {number_b}\n')

def sum_to_file():
    sum_1 = 0
    sum_2 = 0
    with open('/opt/airflow/dags/file.txt', "r+") as file:
        for line in file.readlines():
            val_1, val_2 = line.strip().split(" ")
            sum_1 += int(val_1)
            sum_2 += int(val_2)
            print(f'Values: {val_1}, {val_2}')
        file.write(f'{sum_1-sum_2}')


with DAG(
        dag_id='frstdag',
        start_date=datetime(2022, 12, 13),
        default_args={'retries': 5},
        schedule_interval="1-5 * * * *",
         ) as dag:


    bash_task = BashOperator(task_id='hello', bash_command='echo hello')

    python_task = PythonOperator(task_id='world', python_callable=hello)

    random_print = PythonOperator(task_id='random', python_callable=random_print)

    random_print_file = PythonOperator(task_id='random_print_file', python_callable=random_print_file)

    sum_to_file = PythonOperator(task_id='sum_file', python_callable=sum_to_file)




    bash_task >> python_task >> random_print >> random_print_file >> sum_to_file