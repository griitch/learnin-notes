# Terminology

[Youtube : Prime and Non-prime Attribute](https://www.youtube.com/watch?v=WmBWhjinYxc)

- **Super key** : set of columns that uniquely identify a row, tuples with the same super key value must also have the same non-key attribute values, non-key attributes are functionally dependant on the key attributes.

- **Trivial super key** : the set of all columns, as its guaranteed to be unique ( a super key may contain non unique columns, it's the tuple that must be unique )

- **Candidate key** : minimal super key, subset of the columns of a super key that are unique and on which all other columns are functionally dependant, that is, we cannot further simplify it by removing an attribute

- **Primary key** : same as candidate key

- **Prime attributes** : columns in candidate keys
- **Non-prime attributes** : the rest of columns

# 0nf

each row should be uniquely identified by a key

# 1nf

each cell in a row should contain only one value (does this make postgres array not respect the first form ?)

# 2nf

Tables should not have partial dependencies

example tables :

**student**

| id  | name   | cne        | branch  | address  |
| --- | ------ | ---------- | ------- | -------- |
| 1   | omar   | d138907802 | info    | el houda |
| 3   | gritch | d138905802 | finance | tilila   |
| 2   | yaya   | d138907804 | indus   | el houda |

**subject**

| id  | name |
| --- | ---- |
| 1   | java |
| 2   | cpp  |

**marks**

| student_id | subject_id | mark | teacher_id |
| ---------- | ---------- | ---- | ---------- |
| 1          | 2          | 20   | 1          |
| 2          | 1          | 19   | 3          |
| 1          | 3          | 11   | 4          |

student_id,subject_id -> mark  
subject_id -> teacher_id

this is a partial dependency, as a non prime attribute (the teacher id ) is dependent on a subset of the primary key.  
The solution is to move the teacher id to the subject table

# 3nf

No transitive dependencies
reminder of a transitive dependency :
A -> B -> C.  
A, B and C should not be in the same table, A -> B in its one table, and B -> C in another

# BCNF (Boyce Codd normal form)

aka 3.5nf
A stronger form of the 3nf, it states that for each functional dependency X -> Y of a table, at least one of the following is true :

- X -> Y is a trivial functional dependency
- X is a super key

## 4nf

[youtube : 4nf in plain english](https://www.youtube.com/watch?v=15rpI3W8mdI)

No row contains two or more instances of independent multivalued dependencies.

multivalued dependencies occur when the relation is representing more than one many-to-many / many-to-one relationships

```ts
    type user {
        id : number;
        name : string;
        hobbies : string[];
        languages : string[];
    }

```

hobbies and langs are 2 independent multivalued deps, and should be broken apart into 2 tables
