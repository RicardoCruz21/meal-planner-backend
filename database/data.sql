BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO meal_day (name_of_day) VALUES ('sunday');
INSERT INTO meal_day (name_of_day) VALUES ('monday');
INSERT INTO meal_day (name_of_day) VALUES ('tuesday');
INSERT INTO meal_day (name_of_day) VALUES ('wednesday');
INSERT INTO meal_day (name_of_day) VALUES ('thursday');
INSERT INTO meal_day (name_of_day) VALUES ('friday');
INSERT INTO meal_day (name_of_day) VALUES ('saturday');

INSERT INTO meal_type (meal_type_name) VALUES ('breakfast');
INSERT INTO meal_type (meal_type_name) VALUES ('lunch');
INSERT INTO meal_type (meal_type_name) VALUES ('dinner');
INSERT INTO meal_type (meal_type_name) VALUES ('early snack');
INSERT INTO meal_type (meal_type_name) VALUES ('midday snack');
INSERT INTO meal_type (meal_type_name) VALUES ('late snack');

INSERT INTO category (category_name) VALUES ('mexican');
INSERT INTO category (category_name) VALUES ('chinese');
INSERT INTO category (category_name) VALUES ('italian');

INSERT INTO ingredient (ingredient_name) VALUES ('beef');
INSERT INTO ingredient (ingredient_name) VALUES ('chicken');
INSERT INTO ingredient (ingredient_name) VALUES ('ramen noodles');
INSERT INTO ingredient (ingredient_name) VALUES ('spaghetti');
INSERT INTO ingredient (ingredient_name) VALUES ('tomato sauce');
INSERT INTO ingredient (ingredient_name) VALUES ('green bell pepper');
INSERT INTO ingredient (ingredient_name) VALUES ('rice');
INSERT INTO ingredient (ingredient_name) VALUES ('onion');
INSERT INTO ingredient (ingredient_name) VALUES ('olive oil');

INSERT INTO recipe (recipe_name, instructions, is_sharable) VALUES ('fajitas', 'dice onions and bell peppers, cook beef', false);
INSERT INTO recipe (recipe_name, instructions, is_sharable) VALUES ('spaghetti', 'cook beef and add tomato sauce, cook spaghetti pasta', true);
INSERT INTO recipe (recipe_name, instructions, is_sharable) VALUES ('ramen', 'cook chicken with olive oil, cook ramen noodles', false);

INSERT INTO recipe_ingredient (recipe_id, ingredient_id, quantity, unit_of_measure) VALUES (1, 1, 1, 'pound');
INSERT INTO recipe_ingredient (recipe_id, ingredient_id, quantity, unit_of_measure) VALUES (1, 6, 1, 'each');
INSERT INTO recipe_ingredient (recipe_id, ingredient_id, quantity, unit_of_measure) VALUES (1, 8, 1, 'each');
INSERT INTO recipe_ingredient (recipe_id, ingredient_id, quantity, unit_of_measure) VALUES (2, 1, 1, 'pound');
INSERT INTO recipe_ingredient (recipe_id, ingredient_id, quantity, unit_of_measure) VALUES (2, 4, 1, 'package');
INSERT INTO recipe_ingredient (recipe_id, ingredient_id, quantity, unit_of_measure) VALUES (2, 5, 1, 'jar');
INSERT INTO recipe_ingredient (recipe_id, ingredient_id, quantity, unit_of_measure) VALUES (3, 9, 2, 'tablespoon');
INSERT INTO recipe_ingredient (recipe_id, ingredient_id, quantity, unit_of_measure) VALUES (3, 2, 1, 'pound');
INSERT INTO recipe_ingredient (recipe_id, ingredient_id, quantity, unit_of_measure) VALUES (3, 3, 1, 'package');

INSERT INTO recipe_category (recipe_id, category_id) VALUES (1, 1);
INSERT INTO recipe_category (recipe_id, category_id) VALUES (2, 3);
INSERT INTO recipe_category (recipe_id, category_id) VALUES (3, 2);

INSERT INTO users_recipe (user_id, recipe_id) VALUES (2, 1);
INSERT INTO users_recipe (user_id, recipe_id) VALUES (2, 2);
INSERT INTO users_recipe (user_id, recipe_id) VALUES (2, 3);

COMMIT TRANSACTION;
