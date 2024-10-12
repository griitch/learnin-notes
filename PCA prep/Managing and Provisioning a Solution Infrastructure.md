- a pca needs not only to choose the right services, but also choose the correct configuration for them
- organization policy constraints : values 	that are like a vscode settings.json file, these values can be global or service specific. example of a constraint : the list of regions gcp resources can be provisionned in [full](https://cloud.google.com/resource-manager/docs/organization-policy/org-policy-constraints#available_constraints)

# data storage services
## structured
consider a structured db service (bigtable, firestore, cloud sql)

## unstructured
- use google cloud storage for object storage
	- read less than once per year : archive storage
	- less than once per 90d : coldline storage
	- less than once per 30d : nearline storage
	- standard storage
	- standard storage costs the most, archive costs the less
	- can use 'autoclass' feature that automatically defines the storage class for a bucket based on the frequency of access
			
- in multi region storage we have no control over which regions are the buckets being duplicated in, we can only choose a wide area of near regions (example usa or asia regions only)
- in dual region storage, we can explicitly choose the two regions 
read about .boto config files

min 44 and on
