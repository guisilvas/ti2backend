
Info:
    Server: guilherme.postgres.database.azure.com

    User: sudo
    Password: @pucminas0
    Regra de Firewall: AllowAll_2024-10-17_19-41-52
    IP Inicial: 0.0.0.0

    Commanto to connect: psql --host=guilherme.postgres.database.azure.com --port=5432 --username=sudo --dbname=postgres

DB Commands:
    /*Creating table*/
    CREATE TABLE chessplayers (
        id SERIAL PRIMARY KEY,
        nome VARCHAR(100) NOT NULL,
        rating INT,
        federacao VARCHAR(10),
        titulos_mundiais INT
    );

    /*Inserting data*/
    INSERT INTO chessplayers (nome, rating, federacao, titulos_mundiais) VALUES
    ('Magnus Carlsen', 2861, 'NOR', 5),
    ('Garry Kasparov', 2851, 'RUS', 6),
    ('Bobby Fischer', 2785, 'USA', 1),
    ('Anatoly Karpov', 2725, 'RUS', 3),
    ('Vladimir Kramnik', 2760, 'RUS', 2),
    ('José Raúl Capablanca', 2720, 'CUB', 3),
    ('Mikhail Botvinnik', 2720, 'RUS', 3),
    ('Viswanathan Anand', 2816, 'IND', 5),
    ('Mikhail Tal', 2705, 'LAT', 1),
    ('Vassily Smyslov', 2630, 'RUS', 1),
    ('Tigran Petrosian', 2660, 'ARM', 1),
    ('Alexander Alekhine', 2725, 'FRA', 4),
    ('Paul Keres', 2700, 'EST', 0),
    ('Viktor Korchnoi', 2705, 'SUI', 0),
    ('Ding Liren', 2791, 'CHN', 0),
    ('Fabiano Caruana', 2842, 'USA', 0),
    ('Levon Aronian', 2775, 'ARM', 0),
    ('Wang Hao', 2764, 'CHN', 0),
    ('Vassily Ivanchuk', 2741, 'UKR', 0),
    ('Vladimir Akopian', 2674, 'ARM', 0);

    /*Viewing data*/
    SELECT * FROM chessplayers;

    