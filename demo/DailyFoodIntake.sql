Drop TABLE IF EXISTS daily_food_intake;


CREATE TABLE daily_food_intake (
    food_id serial NOT NULL,
    protein int NOT NULL,
    carbs int NOT NULL,
    fats int NOT NULL,
    meal varchar(100) NOT NULL,
    CONSTRAINT pk_daily_food_intake PRIMARY KEY (food_id),

);


INSERT INTO daily_food_intake (protein, carbs, fats, calories, meal)
VALUES(25, 45, 23, 300, 'lunch');

SELECT *
FROM daily_food_intake;


