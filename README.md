An application that identifies the pair of employees who have worked together on common projects for the longest period of time.

It's given a CSV file with data in the following format:
EmpID, ProjectID, DateFrom, DateTo 

Example data:
1, 1, 2019-07-04, 2020-08-14
1, 2, 2019-12-25, 2020-12-28
1, 3, 2018-10-12, NULL
1, 4, 2019-11-16, NULL
1, 5, 2020-01-05, 2020-12-21
2, 1, 2018-10-03, NULL
2, 2, 2019-01-16, 2020-03-24
2, 3, 2019-05-22, 2019-12-26
2, 4, 2020-03-07, NULL
2, 5, 2018-01-24, 2019-01-15
3, 1, 2019-03-21, 2020-11-26
3, 5, 2019-09-28, 2020-12-25
4, 2, 2018-10-22, NULL
4, 3, 2018-01-27, 2020-08-28
5, 3, 2018-02-03, 2020-10-14
5, 5, 2018-08-04, NULL

Sample output:
Employee #1 ID: 1
Employee #2 ID: 2
Time together: 1784 days.

DateTo can accept value „NULL“ (this is equal to „today“).
The input data must be loaded to the program from a CSV file.

The application has a simple UI - The user picks up a file from the file system and, after selecting it, all common projects of the pair are displayed with the following columns:
Employee ID #1, Employee ID #2, Project ID, Days worked

