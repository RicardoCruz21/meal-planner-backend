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

COMMIT TRANSACTION;
