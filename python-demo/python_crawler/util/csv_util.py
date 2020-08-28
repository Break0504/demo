import csv

def write_csv(csv_name, title):

    f = open(csv_name, 'w', encoding='utf-8', newline='')
    csv_writer = csv.writer(f)
    csv_writer.writerow(title)
    return csv_writer

def write_data(csv_writer, data):
    csv_writer.writerow(data)