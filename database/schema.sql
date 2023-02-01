BEGIN TRANSACTION;

--DROP TABLE IF EXISTS users, meal_plan, users_meal_plan, meal_day, meal_type, meal, meal_entry, meal_plan_meal_entry,
--    recipe, meal_recipe, category, recipe_category, ingredient, recipe_ingredient, grocery_list, grocery_list_ingredient;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE meal_plan (
	meal_plan_id SERIAL,
	meal_plan_name varchar(100) NOT NULL,
	CONSTRAINT PK_meal_plan PRIMARY KEY (meal_plan_id)
);

CREATE TABLE users_meal_plan (
    user_id int NOT NULL,
    meal_plan_id int NOT NULL,
    CONSTRAINT PK_users_meal_plan PRIMARY KEY (user_id, meal_plan_id),
    CONSTRAINT FK_users_meal_plan_users FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT FK_users_meal_plan_meal_plan FOREIGN KEY (meal_plan_id) REFERENCES meal_plan (meal_plan_id)
);

CREATE TABLE meal_day (
    meal_day_id SERIAL,
    name_of_day varchar(10) NOT NULL,
    CONSTRAINT PK_meal_day PRIMARY KEY (meal_day_id)
);

CREATE TABLE meal_type (
    meal_type_id SERIAL,
    meal_type_name varchar(20) NOT NULL,
    CONSTRAINT PK_meal_type PRIMARY KEY (meal_type_id)
);

CREATE TABLE meal (
    meal_id SERIAL,
    meal_name varchar(100) NOT NULL,
    CONSTRAINT PK_meal PRIMARY KEY (meal_id)
);

CREATE TABLE meal_entry (
    meal_entry_id SERIAL,
    meal_day_id int NOT NULL,
    meal_type_id int NOT NULL,
    meal_id int NOT NULL,
    CONSTRAINT PK_meal_entry PRIMARY KEY (meal_entry_id),
    CONSTRAINT FK_meal_entry_meal_day FOREIGN KEY (meal_day_id) REFERENCES meal_day (meal_day_id),
    CONSTRAINT FK_meal_entry_meal_type FOREIGN KEY (meal_type_id) REFERENCES meal_type (meal_type_id),
    CONSTRAINT FK_meal_entry_meal FOREIGN KEY (meal_id) REFERENCES meal (meal_id)
);

CREATE TABLE meal_plan_meal_entry (
    meal_plan_id int NOT NULL,
    meal_entry_id int NOT NULL,
    CONSTRAINT PK_meal_plan_meal_entry PRIMARY KEY (meal_plan_id, meal_entry_id),
    CONSTRAINT FK_meal_plan_meal_entry_meal_plan FOREIGN KEY (meal_plan_id) REFERENCES meal_plan (meal_plan_id),
    CONSTRAINT FK_meal_plan_meal_entry_meal_entry FOREIGN KEY (meal_entry_id) REFERENCES meal_entry (meal_entry_id)
);

CREATE TABLE recipe (
    recipe_id SERIAL,
    recipe_name varchar(100) NOT NULL,
    instructions text,
    is_sharable bool NOT NULL,
    CONSTRAINT PK_recipe PRIMARY KEY (recipe_id)
);

CREATE TABLE users_recipe (
    user_id int NOT NULL,
    recipe_id int NOT NULL,
    CONSTRAINT PK_users_recipe PRIMARY KEY (user_id, recipe_id),
    CONSTRAINT FK_users_recipe_users FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT FK_users_recipe_recipe FOREIGN KEY (recipe_id) REFERENCES recipe (recipe_id)
);

CREATE TABLE meal_recipe (
    meal_id int NOT NULL,
    recipe_id int NOT NULL,
    CONSTRAINT PK_meal_recipe PRIMARY KEY (meal_id, recipe_id),
    CONSTRAINT FK_meal_recipe_meal FOREIGN KEY (meal_id) REFERENCES meal (meal_id),
    CONSTRAINT FK_meal_recipe_recipe FOREIGN KEY (recipe_id) REFERENCES recipe (recipe_id)
);

CREATE TABLE category (
    category_id SERIAL,
    category_name varchar(50) NOT NULL,
    CONSTRAINT PK_category PRIMARY KEY (category_id)
);

CREATE TABLE recipe_category (
    recipe_id int NOT NULL,
    category_id int NOT NULL,
    CONSTRAINT PK_recipe_category PRIMARY KEY (recipe_id, category_id),
    CONSTRAINT FK_recipe_category_recipe FOREIGN KEY (recipe_id) REFERENCES recipe (recipe_id),
    CONSTRAINT FK_recipe_category_category FOREIGN KEY (category_id) REFERENCES category (category_id)
);

CREATE TABLE ingredient (
    ingredient_id SERIAL,
    ingredient_name varchar(100) NOT NULL,
    CONSTRAINT PK_ingredient PRIMARY KEY (ingredient_id)
);

CREATE TABLE recipe_ingredient (
    recipe_id int NOT NULL,
    ingredient_id int NOT NULL,
    quantity numeric(4,2) NOT NULL,
    unit_of_measure varchar(50) NOT NULL,
    CONSTRAINT PK_recipe_ingredient PRIMARY KEY (recipe_id, ingredient_id),
    CONSTRAINT FK_recipe_ingredient_recipe FOREIGN KEY (recipe_id) REFERENCES recipe (recipe_id),
    CONSTRAINT FK_recipe_ingredient_ingredient FOREIGN KEY (ingredient_id) REFERENCES ingredient (ingredient_id)
);

CREATE TABLE grocery_list (
    grocery_list_id SERIAL,
    grocery_list_name varchar(100) NOT NULL,
    CONSTRAINT PK_grocery_list PRIMARY KEY (grocery_list_id)
);

CREATE TABLE grocery_list_ingredient (
    grocery_list_id int NOT NULL,
    ingredient_id int NOT NULL,
    quantity numeric(4,2) NOT NULL,
    unit_of_measure varchar(50) NOT NULL,
    CONSTRAINT PK_grocery_list_ingredient PRIMARY KEY (grocery_list_id, ingredient_id),
    CONSTRAINT FK_grocery_list_ingredient_grocery_list FOREIGN KEY (grocery_list_id) REFERENCES grocery_list (grocery_list_id),
    CONSTRAINT FK_grocery_list_ingredient_ingredient FOREIGN KEY (ingredient_id) REFERENCES ingredient (ingredient_id)
);

COMMIT TRANSACTION;
