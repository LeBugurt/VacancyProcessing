TRUNCATE TABLE tb_hr RESTART IDENTITY CASCADE;
INSERT INTO tb_hr (id, name, email, phone) VALUES
                                               ('c7037c7d-3764-402f-874f-ab24decee3ef', 'Bogdan', 'bogdan@mail.com', '+79192293655');
INSERT INTO tb_hr (id, name, email, phone) VALUES
                                               ('d34a423c-7c90-4773-8f26-566d9a0da843', 'Ivan', 'cser3000@sutulaya.ru', '+79193456685');

INSERT INTO tb_vacancy (id, position, company, salary, currency, description, experience, grade, status, publication_date, created_at, updated_at, hr_id)
VALUES
('c3f792e7-8b9c-4ad1-bb5b-5f6e4d65f1c1', 'Software Engineer', 'Tech Corp', 120000, 'USD', 'Develop and maintain software solutions', '3-5 years', 'Mid', 'Open', '2023-09-01', '2023-09-01 10:15:00', '2023-09-01 10:15:00', 'c7037c7d-3764-402f-874f-ab24decee3ef'),
('9d4f3e5d-7bb1-4903-a7a6-d2750e76dcd2', 'Data Analyst', 'DataWorks', 15000, 'USD', 'Analyze data trends and create reports', '1-2 years', 'Junior', 'Closed', '2023-08-15', '2023-08-15 12:45:00', '2023-09-01 09:30:00', 'c7037c7d-3764-402f-874f-ab24decee3ef'),
('d5b667f9-1d02-4be5-9578-9f92754cf20a', 'Product Manager', 'Innovatech', 150000, 'EUR', 'Lead product development and marketing', '5-7 years', 'Senior', 'Open', '2023-07-10', '2023-07-10 14:00:00', '2023-07-15 11:00:00', 'c7037c7d-3764-402f-874f-ab24decee3ef'),
('af834c8f-3a48-4a8b-bb50-59d045c6db5d', 'HR Specialist', 'PeopleFirst', 80000, 'USD', 'Manage HR processes and recruitment', '2-3 years', 'Mid', 'Open', '2023-09-20', '2023-09-20 16:30:00', '2023-09-22 08:00:00', 'd34a423c-7c90-4773-8f26-566d9a0da843'),
('f75f314e-b0de-49c6-8dbf-815b929fbbf4', 'UX Designer', 'Creative Minds', 95000, 'USD', 'Design user-friendly interfaces and improve UX', '3-5 years', 'Mid', 'Open', '2023-10-01', '2023-10-01 09:00:00', '2023-10-01 09:00:00', 'd34a423c-7c90-4773-8f26-566d9a0da843');

-- Вставка данных в таблицу hr_vacancy
INSERT INTO tb_hr_favourites (id, id_hr, id_vacancy) VALUES
                                                      ('e4a8013a-730e-4ab6-9b8a-ab898d5ba84c', 'c7037c7d-3764-402f-874f-ab24decee3ef', 'af834c8f-3a48-4a8b-bb50-59d045c6db5d'),
                                                      ('acfe60d5-1129-49c4-9f0a-58860b9f6917', 'c7037c7d-3764-402f-874f-ab24decee3ef', 'f75f314e-b0de-49c6-8dbf-815b929fbbf4'),
                                                      ('56a576c3-ce36-43aa-bb3b-698bc262dbc3', 'd34a423c-7c90-4773-8f26-566d9a0da843', 'c3f792e7-8b9c-4ad1-bb5b-5f6e4d65f1c1'),
                                                      ('90dda969-0bcf-4d1e-8109-f3b8e8f7e2a3', 'd34a423c-7c90-4773-8f26-566d9a0da843', '9d4f3e5d-7bb1-4903-a7a6-d2750e76dcd2');

TRUNCATE TABLE tb_location RESTART IDENTITY CASCADE;
INSERT INTO tb_location (id, country, city, region) VALUES
    ('1f3a65e0-ec75-4b90-a2f4-78f564f41b4e', 'USA', 'New York', 'New York');

INSERT INTO tb_location (id, country, city, region) VALUES
    ('2b7c06f2-4184-432f-a8f5-3841af56b582', 'Canada', 'Toronto', 'Ontario');

INSERT INTO tb_location (id, country, city, region) VALUES
    ('3c8b4b4e-e4d6-4d59-94e7-87a5a8f8c6b6', 'Germany', 'Berlin', 'Berlin');

INSERT INTO tb_location (id, country, city, region) VALUES
    ('4df75a2d-fb18-42a9-bd4d-d2c57eb7c1bc', 'Japan', 'Tokyo', 'Kanto');

INSERT INTO tb_location (id, country, city, region) VALUES
    ('5ef37ad2-8f1e-4f9e-874f-d5a9a1e14757', 'Russia', 'Moscow', 'Moscow');

INSERT INTO tb_vacancy_location (id, id_vacancy, id_location) VALUES
                                                                  ('0dd7e433-f55e-4669-a298-8da68ebfe2e7', 'c3f792e7-8b9c-4ad1-bb5b-5f6e4d65f1c1', '1f3a65e0-ec75-4b90-a2f4-78f564f41b4e'),
                                                                  ('cef6f523-8f3c-4e9e-94d4-f5bd5ed4e679', '9d4f3e5d-7bb1-4903-a7a6-d2750e76dcd2', '2b7c06f2-4184-432f-a8f5-3841af56b582'),
                                                                  ('c20ce99c-8e11-41bc-9e68-614799505878', 'd5b667f9-1d02-4be5-9578-9f92754cf20a', '3c8b4b4e-e4d6-4d59-94e7-87a5a8f8c6b6'),
                                                                  ('8a98b2a7-1a34-4e24-beac-a07412d47ca5', 'af834c8f-3a48-4a8b-bb50-59d045c6db5d', '4df75a2d-fb18-42a9-bd4d-d2c57eb7c1bc'),
                                                                  ('b09deb83-5048-4b17-adfe-54f941a2a685', 'f75f314e-b0de-49c6-8dbf-815b929fbbf4', '5ef37ad2-8f1e-4f9e-874f-d5a9a1e14757');

INSERT INTO tb_vacancy_skill (id, skill, vacancy_id) VALUES
                                                         ('06b31535-c72b-499d-a866-68cbd91b6960', 'Java', 'c3f792e7-8b9c-4ad1-bb5b-5f6e4d65f1c1'),
                                                         ('3dc07f4f-e409-48e8-91b3-3599548b2dad', 'SQL', 'c3f792e7-8b9c-4ad1-bb5b-5f6e4d65f1c1'),
                                                         ('2637b384-27ae-445f-aa65-988a2e0e275f', 'Python', '9d4f3e5d-7bb1-4903-a7a6-d2750e76dcd2'),
                                                         ('479575bc-86f6-4628-a56b-2f21ea0ebfd1', 'SQL', 'd5b667f9-1d02-4be5-9578-9f92754cf20a'),
                                                         ('278a4f31-4950-4efa-a236-ad35fa5af2ef', 'Management', 'd5b667f9-1d02-4be5-9578-9f92754cf20a'),
                                                         ('ccbf5baa-8e1b-4237-ba60-9c754c95b38f', 'TvoritHuynyu', 'af834c8f-3a48-4a8b-bb50-59d045c6db5d'),
                                                         ('0a9b9239-1b7e-46c8-acad-db93d60edf57', 'Design', 'f75f314e-b0de-49c6-8dbf-815b929fbbf4');
