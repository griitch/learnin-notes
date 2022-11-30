# Def

The n+1 query problem happens when the code (the orm) executes N additional queries to fetch the same data it that could have been fetched by executing one primary query

# Example

### Data model

2 tables, ingredients and recipes, each recipe have many ingredients, and each ingredient can be a part of any recipe (or none)

### Pseudo code

```ts

Recipe {
    int id;
    String name;
    List<Ingredient> ingredients;
}
Ingredient {
    int id;
    String name;
}

@getMapping
getAllRecipes() {
    return Recipe.getAll();
}

@View
displayRecipes() {
    getAllRecipes().forEach(
        r => {
            display(r)
            r.getIngredients().forEach(display);
            // this right here is causing the problem

        }
    )
}
```

This code will cause the orm to make a sql query for each ingredient in the recipe, in addition the sql query made to get the recipe data.

## Solution

the solution is dead simple, just batch elements retrieval when possible, ORMs for sure have options for doing it, spring data jpa for example uses eager loading
