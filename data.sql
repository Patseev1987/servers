INSERT INTO public.users (username, role, password)
VALUES ('andrey', 'USER', '1'),
       ('ivan', 'USER', '1'),
       ('sergey', 'USER', '1'),
       ('storage', 'USER', '1'),
       ('fedor', 'USER', '1'),
       ('alex', 'USER', '1'),
       ('storage_sharpen', 'USER', '1'),
       ('bad_tool', 'USER', '1');

INSERT INTO public.workers (id, department, first_name, join_date, last_name, login, patronymic, type)
VALUES (1, 'DEPARTMENT_19', 'Андрей', '2019-03-01', 'Иванов', 'andrey', 'Иванович', 'WORKER'),
       (2, 'DEPARTMENT_19', 'Иван', '2022-05-01', 'Сергеев', 'ivan', 'Иванович', 'WORKER'),
       (3, 'DEPARTMENT_19', 'Сергей', '2011-06-01', 'Фирсов', 'sergey', 'Сергеевич', 'WORKER'),
       (4, 'DEPARTMENT_19', 'Кладовая', '1900-03-01', 'Кладовая', 'storage', 'Кладовая', 'STORAGE_WORKER'),
       (5, 'DEPARTMENT_19', 'Федор', '2015-04-01', 'Цаплин', 'fedor', 'Сергеевич', 'WORKER'),
       (6, 'DEPARTMENT_19', 'Алексей', '2023-03-01', 'Федоров', 'alex', 'Сергеевич', 'WORKER'),
       (7, 'SHARPENING', 'Заточка', '1900-03-01', 'Заточка', 'storage_sharpen', 'Заточка', 'STORAGE_WORKER'),
       (8, 'STORAGE_OF_DECOMMISSIONED_TOOLS', 'Списание', '1900-03-01', 'Списание', 'bad_tool', 'Списание',
        'STORAGE_WORKER');

INSERT INTO public.tools (code, additional_info, description, icon, name, place_column, place_row, place_shelf, type)
VALUES ('2004-9060', 'some info', 'some inner',
        'https://5.imimg.com/data5/SELLER/Default/2023/6/320127867/TQ/JD/GT/2304796/turning-inserts-500x500.jpg',
        'CNMG120408', '1', '1', '1', 'CUTTING'),
       ('2004-1001', 'some info', 'some inner',
        'https://5.imimg.com/data5/SELLER/Default/2023/6/320127867/TQ/JD/GT/2304796/turning-inserts-500x500.jpg',
        'DNMG150608', '1', '1', '1', 'CUTTING'),
       ('2004-10111', 'some info', 'some inner',
        'https://5.imimg.com/data5/SELLER/Default/2023/6/320127867/TQ/JD/GT/2304796/turning-inserts-500x500.jpg',
        'VCMT160408', '1', '1', '1', 'CUTTING'),
       ('2004-7480', 'some info', 'some inner',
        'https://5.imimg.com/data5/SELLER/Default/2023/6/320127867/TQ/JD/GT/2304796/turning-inserts-500x500.jpg',
        'GRIP5025', '1', '1', '1', 'CUTTING'),
       ('8700-0001', 'some info', 'some measure tool',
        'https://www.beta-tools.com/media/catalog/category/x10_news_05.jpg', 'Measure tool #1', '2', '2', '2',
        'MEASURE'),
       ('8700-2001', 'some info', 'some measure tool',
        'https://www.beta-tools.com/media/catalog/category/x10_news_05.jpg', 'Measure tool #2', '2', '2', '2',
        'MEASURE'),
       ('8700-0987', 'some info', 'some measure tool',
        'https://www.beta-tools.com/media/catalog/category/x10_news_05.jpg', 'Measure tool #3', '2', '2', '2',
        'MEASURE'),
       ('6331-2222', 'some info', 'some helper tool',
        'https://ae04.alicdn.com/kf/S530fdd8cb379432a9732144100d99820w.jpg', 'Block for tools', '3', '3', '3',
        'HELPERS'),
       ('6075-1331', 'some info', 'some helper tool',
        'https://ae04.alicdn.com/kf/S530fdd8cb379432a9732144100d99820w.jpg', 'Center for machine', '3', '3', '3',
        'HELPERS'),
       ('6331-8065', 'some info', 'some helper tool',
        'https://ae04.alicdn.com/kf/S530fdd8cb379432a9732144100d99820w.jpg', 'Helper #1', '3', '3', '3', 'HELPERS');

INSERT INTO public.tools_transactions (code, name)
VALUES ('2004-9060', 'CNMG120408'),
       ('2004-1001', 'DNMG150608'),
       ('2004-10111', 'VCMT160408'),
       ('2004-7480', 'GRIP5025'),
       ('8700-0001', 'Measure tool #1'),
       ('8700-2001', 'Measure tool #2'),
       ('8700-0987', 'Measure tool #3'),
       ('6331-2222', 'Block for tools'),
       ('6075-1331', 'Center for machine'),
       ('6331-8065', 'Helper #1');

INSERT INTO public.workers_transaction (id, department, first_name, last_name)
VALUES (1, 'DEPARTMENT_19', 'Андрей', 'Иванов'),
       (2, 'DEPARTMENT_19', 'Иван', 'Сергеев'),
       (3, 'DEPARTMENT_19', 'Сергей', 'Фирсов'),
       (4, 'DEPARTMENT_19', 'Кладовая', 'Кладовая'),
       (5, 'DEPARTMENT_19', 'Федор', 'Цаплин'),
       (6, 'DEPARTMENT_19', 'Алексей', 'Федоров'),
       (7, 'SHARPENING', 'Заточка', 'Заточка'),
       (8, 'STORAGE_OF_DECOMMISSIONED_TOOLS', 'Списание', 'Списание');


INSERT INTO public.storage_records ( amount, tool_code, worker_id)
VALUES ( 400, '2004-9060', 4),
       (500, '2004-1001', 4),
       (200, '2004-10111', 4),
       (1000, '2004-7480', 4),
       (20, '8700-0001', 4),
       (30, '8700-2001', 4),
       (25, '8700-0987', 4),
       (10, '6331-2222', 4),
       (15, '6075-1331', 4),
       (5, '6331-8065', 4);

INSERT INTO public.jaws ( description, name, operation_number, place_column, place_row, place_shelf)
VALUES ('for turing machine', '026.16.00.011', 'for turning machine like HWACHEON', '1', '2', '3'),
       ('for turing machine', '026.16.00.012', 'for turning machine like HWACHEON', '4', '5', '6'),
       ('for turing machine', '026.16.00.013', 'for turning machine like HWACHEON', '7', '8', '9'),
       ('for turing machine', '026.16.00.014', 'for turning machine like HWACHEON', '1', '6', '7'),
       ('for turing machine', '026.16.00.015', 'for turning machine like HWACHEON', '2', '2', '6'),
       ('for turing machine', '026.16.00.016', 'for turning machine like HWACHEON', '1', '4', '2');