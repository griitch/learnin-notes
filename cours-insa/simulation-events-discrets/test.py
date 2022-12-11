import ciw

N = ciw.create_network(
    arrival_distributions=[['Exponential',0.2]],
    service_distributions=[['Exponential',0.2]],
    number_of_servers=[3]
)


