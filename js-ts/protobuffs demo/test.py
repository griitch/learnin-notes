from operator import eq
import employee_pb2

emp = employee_pb2.Employee()

emp.id = 1
emp.name = "John Doe"
emp.department = "IT"
emp.salary = 1000


serialized = emp.SerializeToString()
# prints b'\x08\x01\x12\x08John Doe\x1a\x02IT%\x00\x00zD'

print(serialized)

emp2 = employee_pb2.Employee()
emp2.ParseFromString(serialized)
# ParseFromString() doesn't return anything, it fills in self with the parsed data

print(eq(emp, emp2))  # true
