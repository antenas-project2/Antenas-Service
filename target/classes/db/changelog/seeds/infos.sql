INSERT INTO address
(id, place, `number`, street, neighborhood, city, zip_code)
VALUES(1, '', 0, '', '', '', '');
INSERT INTO address
(id, place, `number`, street, neighborhood, city, zip_code)
VALUES(2, '', 123, 'Rua Moxoto', 'Chácaras Reunidas', 'São José dos Campos', '12238-320');


-- Fase 2
INSERT INTO project
(id, teacher_id, meeting_id, title, short_description, complete_description, technology_description, notes, updated_at, created_at, progress, refused, reason, `open`, finished, finished_by, approved_by, created_by, course, semester)
VALUES(1, NULL, NULL, 'projetinho fase 2', 'Ipsum volutpat eleifend dolor dignissim interdum vivamus porta. Vehicula litora tristique consequat tincidunt imperdiet sem rutrum in dictumst. Erat morbi mollis vestibulum habitasse rutrum viverra pulvinar id dictum ridiculus? Augue hendrerit dictumst potenti euismod mattis mauris nascetur turpis. Netus pellentesque semper facilisis suspendisse dictumst. Metus volutpat ullamcorper porta sociosqu habitant blandit tempor conubia netus nec fames. Est elementum!', NULL, NULL, 'Fames volutpat venenatis primis porta placerat erat elit consequat. In consequat sit egestas curabitur feugiat cum lorem libero per mauris? Euismod ante orci pharetra! Torquent vestibulum porta lobortis ultrices. Hac justo facilisis cras eleifend accumsan augue neque mauris neque. Sollicitudin lacin', NULL, '2020-11-17 03:35:28', 2, NULL, NULL, 0, NULL, NULL, NULL, 3, NULL, 0);


-- Fase 3
INSERT INTO meeting
(id, address_id, `date`)
VALUES(1, 1, NULL);

INSERT INTO project
(id, teacher_id, meeting_id, title, short_description, complete_description, technology_description, notes, updated_at, created_at, progress, refused, reason, `open`, finished, finished_by, approved_by, created_by, course, semester)
VALUES(3, NULL, 2, 'projeto fase 3', 'Ipsum volutpat eleifend dolor dignissim interdum vivamus porta. Vehicula litora tristique consequat tincidunt imperdiet sem rutrum in dictumst. Erat morbi mollis vestibulum habitasse rutrum viverra pulvinar id dictum ridiculus? Augue hendrerit dictumst potenti euismod mattis mauris nascetur turpis. Netus pellentesque semper facilisis suspendisse dictumst. Metus volutpat ullamcorper porta sociosqu habitant blandit tempor conubia netus nec fames. Est elementum!', NULL, NULL, 'Placerat aptent nostra condimentum libero mus pharetra lacus mauris. Cursus ut habitasse nisi tempor eu semper laoreet per scelerisque. Cras rutrum euismod aptent elementum. Pretium hac posuere taciti netus natoque. Dictum ut nisl ipsum morbi turpis ornare sociis penatibus egestas fermentum suscipit', '2020-11-17 03:42:03', '2020-11-17 03:36:28', 3, 0, NULL, 0, NULL, NULL, NULL, 3, NULL, 0);


-- Fase 4
INSERT INTO meeting
(id, address_id, `date`)
VALUES(2, 1, NULL);

INSERT INTO project
(id, teacher_id, meeting_id, title, short_description, complete_description, technology_description, notes, updated_at, created_at, progress, refused, reason, `open`, finished, finished_by, approved_by, created_by, course, semester)
VALUES(2, NULL, 1, 'projetinho fase 4', 'Fames volutpat venenatis primis porta placerat erat elit consequat. In consequat sit egestas curabitur feugiat cum lorem libero per mauris? Euismod ante orci pharetra! Torquent vestibulum porta lobortis ultrices. Hac justo facilisis cras eleifend accumsan augue neque mauris neque. Sollicitudin lacinia sed tempor praesent arcu torquent. Senectus sit, interdum ad. Ut curae; sagittis leo luctus tortor erat parturient parturient nisi orci consectetur. Vivamus placerat faucibus proin feugiat eros ut vehicula bibendum porttitor adipiscing massa euismod. Ornare natoque.', 'Phasellus vel tempus interdum mollis hendrerit pharetra venenatis condimentum. Integer, velit consectetur nascetur hendrerit at. Eros commodo nunc, gravida in integer. Lobortis cursus augue habitasse venenatis mauris ridiculus facilisis nunc. Magnis magna congue aliquam ac in feugiat nec mauris felis placerat. Erat lobortis congue laoreet est dictum fames congue quis arcu dictum cras. Ac cubilia penatibus ligula magna vel fermentum natoque himenaeos augue ridiculus ultrices? Diam fusce blandit turpis ad tempor volutpat curabitur lectus venenatis ligula. Laoreet neque at praesent sit. Taciti velit risus vitae dignissim quis elit mollis risus primis. Faucibus, convallis curae; eros tortor? Egestas arcu dapibus.', '
Ridiculus cursus viverra condimentum ullamcorper justo eget dictum erat, pharetra integer facilisis commodo. Vulputate pulvinar montes imperdiet habitant malesuada morbi dictumst hac erat. Nibh euismod libero ridiculus orci nisi. Potenti praesent placerat at dictum cum augue dictumst inceptos commodo. Habitant metus ut tortor euismod fermentum arcu imperdiet porta. Amet gravida ullamcorper vel proin. Sapien proin, tempus dis. Egestas libero imperdiet malesuada non curabitur vehicula sem lacinia. Fames nostra.', 'Ipsum volutpat eleifend dolor dignissim interdum vivamus porta. Vehicula litora tristique consequat tincidunt imperdiet sem rutrum in dictumst. Erat morbi mollis vestibulum habitasse rutrum viverra pulvinar id dictum ridiculus? Augue hendrerit dictumst potenti euismod mattis mauris nascetur turpis. ', '2020-11-17 03:43:27', '2020-11-17 03:35:47', 4, 0, NULL, 0, NULL, NULL, NULL, 3, NULL, 0);


-- Fase 5 (aguardando cadi)
INSERT INTO `date`
(id, `date`)
VALUES(1, '2020-11-17 03:45:00');
INSERT INTO `date`
(id, `date`)
VALUES(2, '2020-11-27 03:50:00');
INSERT INTO `date`
(id, `date`)
VALUES(3, '2020-11-27 08:55:00');

INSERT INTO meeting
(id, address_id, `date`)
VALUES(4, 1, NULL);

INSERT INTO meeting_date
(id, meeting_id, date_id)
VALUES(1, 4, 1);
INSERT INTO meeting_date
(id, meeting_id, date_id)
VALUES(2, 4, 2);
INSERT INTO meeting_date
(id, meeting_id, date_id)
VALUES(3, 4, 3);


INSERT INTO project
(id, teacher_id, meeting_id, title, short_description, complete_description, technology_description, notes, updated_at, created_at, progress, refused, reason, `open`, finished, finished_by, approved_by, created_by, course, semester)
VALUES(4, NULL, 4, '5 - aguardando cadi', 'Placerat aptent nostra condimentum libero mus pharetra lacus mauris. Cursus ut habitasse nisi tempor eu semper laoreet per scelerisque. Cras rutrum euismod aptent elementum. Pretium hac posuere taciti netus natoque. Dictum ut nisl ipsum morbi turpis ornare sociis penatibus egestas fermentum suscipit. Rutrum semper iaculis nostra litora cras facilisi. Phasellus ante in convallis sapien ad integer mattis pellentesque ridiculus inceptos.', '
Ridiculus cursus viverra condimentum ullamcorper justo eget dictum erat, pharetra integer facilisis commodo. Vulputate pulvinar montes imperdiet habitant malesuada morbi dictumst hac erat. Nibh euismod libero ridiculus orci nisi. Potenti praesent placerat at dictum cum augue dictumst inceptos commodo. Habitant metus ut tortor euismod fermentum arcu imperdiet porta. Amet gravida ullamcorper vel proin. Sapien proin, tempus dis. Egestas libero imperdiet malesuada non curabitur vehicula sem lacinia. Fames nostra.', 'Massa nam netus vivamus ante class per. Ridiculus at libero bibendum lacinia consequat? Congue quisque lectus porttitor ridiculus bibendum suscipit lorem et vulputate phasellus justo! Faucibus ultricies ultrices aliquet vulputate tempus etiam dui tortor et sodales? Non felis nibh viverra viverra vel tempor volutpat ullamcorper convallis leo habitant ad. Dapibus est bibendum velit condimentum rutrum', 'Donec cursus a aptent et purus suscipit vivamus ornare. Duis ac aliquet, tincidunt potenti. Rhoncus arcu nunc faucibus sodales conubia. Ad, dolor libero sapien! Sociis tempor, viverra blandit varius montes mattis egestas. Viverra cubilia per pharetra dignissim nostra? Mi faucibus lacus condimentum t', '2020-11-17 03:45:44', '2020-11-17 03:36:48', 5, 0, NULL, 0, NULL, NULL, NULL, 3, NULL, 0);


-- Fase 5 (aguardando representante)
INSERT INTO meeting
(id, address_id, `date`)
VALUES(5, 2, NULL);

INSERT INTO project
(id, teacher_id, meeting_id, title, short_description, complete_description, technology_description, notes, updated_at, created_at, progress, refused, reason, `open`, finished, finished_by, approved_by, created_by, course, semester)
VALUES(5, NULL, 5, '5- aguardando rep', 'Donec cursus a aptent et purus suscipit vivamus ornare. Duis ac aliquet, tincidunt potenti. Rhoncus arcu nunc faucibus sodales conubia. Ad, dolor libero sapien! Sociis tempor, viverra blandit varius montes mattis egestas. Viverra cubilia per pharetra dignissim nostra? Mi faucibus lacus condimentum turpis quam ligula ipsum. Facilisi facilisis consectetur euismod. Potenti adipiscing primis fringilla fermentum faucibus. Justo proin ac vel neque nullam est pellentesque semper semper. Amet sagittis, eu nec. Class conubia?', 'Condimentum eleifend donec orci felis enim, taciti et lacus sed praesent. Nunc proin pulvinar porttitor suscipit lectus placerat tempor iaculis nunc nec suspendisse litora. Congue lectus cras ac vitae primis arcu quis aenean libero! Maecenas, sagittis luctus quisque. Commodo rhoncus lorem ipsum ultricies magna felis. Proin morbi eleifend sit vehicula pretium cubilia. Congue nisi tempus inceptos vulputate aenean. Ut ultrices!', 'Hendrerit lorem himenaeos vestibulum dapibus scelerisque fringilla a habitasse taciti consectetur sem. Lacinia ornare est tempus, placerat molestie vivamus accumsan. Dapibus nisl arcu laoreet mauris at per integer lorem placerat nibh integer! Quam aliquam convallis lobortis penatibus dictum. Auctor morbi fringilla luctus facilisi ultrices cras quisque porta elit habitasse nunc? Dolor dignissim cursus natoque neque eu conubia? Velit parturient nullam tortor sociosqu nibh praesent dictum magna elementum sit euismod ultricies. Consectetur auctor curae; cubilia dui dictumst consectetur, lorem cras. Etiam faucibus placerat in lobortis natoque urna vehicula vehicula euismod rutrum ultricies orci.', 'Magna ad sociis, fringilla fames. Class himenaeos class sagittis. Dapibus, ligula et himenaeos dictumst molestie. Eleifend hac penatibus penatibus justo magna dignissim cubilia facilisi nascetur aliquam. Primis, maecenas pretium viverra parturient sollicitudin eleifend. Magna, sagittis sagittis ridi', '2020-11-17 03:46:53', '2020-11-17 03:37:25', 5, 0, NULL, 0, NULL, NULL, NULL, 3, NULL, 0);


-- Fase 6
INSERT INTO meeting
(id, address_id, `date`)
VALUES(6, 2, '2020-11-17 03:45:00');

INSERT INTO project
(id, teacher_id, meeting_id, title, short_description, complete_description, technology_description, notes, updated_at, created_at, progress, refused, reason, `open`, finished, finished_by, approved_by, created_by, course, semester)
VALUES(6, NULL, 6, 'fase 6', 'Dolor sollicitudin fames turpis luctus eros rhoncus. Porta at fermentum viverra neque ligula nulla nam, aptent nam. Sit quis class, parturient felis. Elit phasellus tellus imperdiet hac nibh facilisis quisque. Nisi lacinia orci mi est feugiat felis porttitor purus blandit molestie porttitor cum. Lectus amet vulputate ullamcorper porta etiam aenean vitae ullamcorper pellentesque quam. Nullam etiam placerat dignissim diam nascetur. Dui, dolor platea platea. Auctor montes habitant augue habitant euismod proin laoreet. Molestie ad sagittis himenaeos aptent. Sit molestie felis', 'Hendrerit lorem himenaeos vestibulum dapibus scelerisque fringilla a habitasse taciti consectetur sem. Lacinia ornare est tempus, placerat molestie vivamus accumsan. Dapibus nisl arcu laoreet mauris at per integer lorem placerat nibh integer! Quam aliquam convallis lobortis penatibus dictum. Auctor morbi fringilla luctus facilisi ultrices cras quisque porta elit habitasse nunc? Dolor dignissim cursus natoque neque eu conubia? Velit parturient nullam tortor sociosqu nibh praesent dictum magna elementum sit euismod ultricies. Consectetur auctor curae; cubilia dui dictumst consectetur, lorem cras. Etiam faucibus placerat in lobortis natoque urna vehicula vehicula euismod rutrum ultricies orci.', 'Dictum massa porta morbi senectus torquent magnis egestas sem consequat scelerisque iaculis congue. Fringilla aptent risus pulvinar habitasse fames aptent suscipit suscipit bibendum arcu. Fames varius sapien feugiat pretium cum nec montes habitant urna vestibulum consequat nostra. Porttitor primis curae; dolor venenatis torquent sapien curabitur cubilia congue etiam! Parturient pulvinar.', 'Nunc id vulputate quam dictum est. Per potenti rutrum libero feugiat curae; habitasse? Feugiat rhoncus bibendum neque nam curae; magna dis cubilia facilisis nascetur pulvinar. Aenean tincidunt justo, aptent duis nec. Hendrerit consectetur varius facilisi faucibus fames maecenas. Porttitor lectus fac', '2020-11-17 03:48:38', '2020-11-17 03:39:45', 6, 0, NULL, 0, NULL, NULL, NULL, 3, NULL, 0);



-- Fase 7
INSERT INTO meeting
(id, address_id, `date`)
VALUES(7, 2, '2020-11-18 03:45:00');

INSERT INTO project
(id, teacher_id, meeting_id, title, short_description, complete_description, technology_description, notes, updated_at, created_at, progress, refused, reason, `open`, finished, finished_by, approved_by, created_by, course, semester)
VALUES(7, 2, 7, 'Tá na fase 7', 'Nunc id vulputate quam dictum est. Per potenti rutrum libero feugiat curae; habitasse? Feugiat rhoncus bibendum neque nam curae; magna dis cubilia facilisis nascetur pulvinar. Aenean tincidunt justo, aptent duis nec. Hendrerit consectetur varius facilisi faucibus fames maecenas. Porttitor lectus facilisis praesent bibendum ridiculus est hac. Torquent ultricies faucibus elementum. Dictum nibh tempor tempor ipsum parturient purus sapien nunc iaculis? Nam leo accumsan commodo eu dictumst laoreet dis penatibus viverra justo. At mi tincidunt metus at urna cras porttitor massa. Dolor?', 'Dictum massa porta morbi senectus torquent magnis egestas sem consequat scelerisque iaculis congue. Fringilla aptent risus pulvinar habitasse fames aptent suscipit suscipit bibendum arcu. Fames varius sapien feugiat pretium cum nec montes habitant urna vestibulum consequat nostra. Porttitor primis curae; dolor venenatis torquent sapien curabitur cubilia congue etiam! Parturient pulvinar.', 'Habitant lobortis convallis ligula vel platea tempor semper. Fringilla, torquent cubilia vivamus nunc elit donec nibh pulvinar. Quisque aenean venenatis maecenas aliquet ultricies tellus sociis, eu vitae vivamus. Class donec conubia convallis potenti. Tempus molestie orci morbi, facilisis diam praesent. Vel sit condimentum aliquam vitae dui, fames orci gravida accumsan. Euismod elementum tellus suscipit nascetur. Lacinia semper massa eleifend!', 'Maecenas ornare praesent habitant diam iaculis ligula non platea eget sodales ut facilisis? Elit elementum integer enim lacus dui semper montes quisque curae;, pharetra vulputate risus. Consequat ad, porta turpis. Bibendum ante, lectus facilisi dis. Sit suspendisse imperdiet faucibus ridiculus sempe', '2020-11-17 03:49:15', '2020-11-17 03:40:10', 7, 0, NULL, 0, NULL, NULL, NULL, 3, NULL, 5);


-- Fase 8
INSERT INTO meeting
(id, address_id, `date`)
VALUES(8, 2, '2020-11-17 03:55:00');

INSERT INTO project
(id, teacher_id, meeting_id, title, short_description, complete_description, technology_description, notes, updated_at, created_at, progress, refused, reason, `open`, finished, finished_by, approved_by, created_by, course, semester)
VALUES(8, 2, 8, ' 8 - projeto iniciado', 'Maecenas ornare praesent habitant diam iaculis ligula non platea eget sodales ut facilisis? Elit elementum integer enim lacus dui semper montes quisque curae;, pharetra vulputate risus. Consequat ad, porta turpis. Bibendum ante, lectus facilisi dis. Sit suspendisse imperdiet faucibus ridiculus semper nulla. Iaculis nunc inceptos viverra tortor! Interdum sapien ad netus curabitur imperdiet, eros primis nam sapien. Accumsan elementum posuere senectus lacinia sodales hac.', 'Cum laoreet scelerisque eros malesuada nam. Elit ipsum congue fermentum placerat molestie tristique turpis blandit. Gravida porttitor potenti dapibus. Platea nascetur consequat conubia volutpat nec class odio. Habitant at amet mollis! Leo class porta torquent cras mi arcu commodo curae;. Fames potenti nascetur himenaeos amet diam himenaeos aliquet. Ullamcorper dignissim vehicula rutrum laoreet iaculis aenean. Iaculis venenatis primis commodo imperdiet sodales cras, fusce tincidunt. Commodo dictumst tellus molestie et. Leo aenean odio enim cursus.', 'Habitasse rhoncus himenaeos aenean potenti amet aliquam tristique. Morbi tristique, sociis habitant. Risus turpis torquent facilisis rhoncus gravida facilisi in auctor aliquam habitant fermentum. Ultricies felis facilisis, justo blandit risus neque sagittis praesent gravida pellentesque! Placerat hac dictumst nisi lorem sit habitasse. Rhoncus felis auctor sagittis dolor. Aliquam eget convallis dolor penatibus semper leo inceptos eu! Fringilla ac in cum orci inceptos lacinia volutpat dictum. Curabitur elementum sem cursus molestie quis convallis tortor. Massa rhoncus sed dictum sed.', 'Potenti laoreet, molestie ultricies pellentesque ligula ridiculus scelerisque lacus cursus elit viverra justo. Erat interdum dictum primis vestibulum curae; litora hac montes porttitor sollicitudin. Cras lectus auctor, fames dignissim conubia nibh sapien quisque. Nisi duis platea eget felis neque qu', '2020-11-17 03:49:47', '2020-11-17 03:40:32', 8, 0, NULL, 1, NULL, NULL, NULL, 3, NULL, 6);

INSERT INTO team
(id, project_id, name, project_url, communication_link)
VALUES(1, 8, 'equipe topzera', 'github.com', 'slack.com');

INSERT INTO student_team
(id, team_id, student_id, `role`)
VALUES(1, 1, 1, 'Scrum Master');




-- Fase 9
INSERT INTO meeting
(id, address_id, `date`)
VALUES(9, 2, '2020-11-17 03:50:00');

INSERT INTO project
(id, teacher_id, meeting_id, title, short_description, complete_description, technology_description, notes, updated_at, created_at, progress, refused, reason, `open`, finished, finished_by, approved_by, created_by, course, semester)
VALUES(9, 2, 9, '9 cabooo o projeto', 'Potenti laoreet, molestie ultricies pellentesque ligula ridiculus scelerisque lacus cursus elit viverra justo. Erat interdum dictum primis vestibulum curae; litora hac montes porttitor sollicitudin. Cras lectus auctor, fames dignissim conubia nibh sapien quisque. Nisi duis platea eget felis neque quis proin litora dapibus quisque semper! Tortor ultricies volutpat malesuada molestie pellentesque himenaeos eros montes vehicula! Duis lorem condimentum sodales, ullamcorper inceptos. Faucibus integer placerat dictum neque.', 'Habitasse rhoncus himenaeos aenean potenti amet aliquam tristique. Morbi tristique, sociis habitant. Risus turpis torquent facilisis rhoncus gravida facilisi in auctor aliquam habitant fermentum. Ultricies felis facilisis, justo blandit risus neque sagittis praesent gravida pellentesque! Placerat hac dictumst nisi lorem sit habitasse. Rhoncus felis auctor sagittis dolor. Aliquam eget convallis dolor penatibus semper leo inceptos eu! Fringilla ac in cum orci inceptos lacinia volutpat dictum. Curabitur elementum sem cursus molestie quis convallis tortor. Massa rhoncus sed dictum sed.', 'Eleifend, tincidunt facilisis primis leo commodo posuere. Praesent habitant parturient curabitur. Feugiat montes tempor feugiat dictum eget feugiat? Rhoncus viverra sapien conubia volutpat mollis amet? Elit odio lobortis suspendisse cursus bibendum nam eros est eros placerat venenatis molestie! Quam gravida rutrum justo placerat! Natoque, tincidunt curabitur euismod facilisis commodo volutpat auctor diam quam donec amet aliquet. Purus urna vitae per purus mus. Nam pretium ut sapien! Ac orci magna consequat nec neque urna ultrices placerat netus pulvinar sem. Habitasse habitasse fusce magnis!', 'Mattis, netus ante platea sodales sociosqu purus. Aptent potenti quisque natoque penatibus tempus? Platea eu dictumst in dictum dictumst cum donec ligula diam. Et vestibulum fames varius taciti? Turpis fermentum adipiscing metus aptent dapibus facilisis turpis, egestas ornare elit parturient. Gravid', '2020-11-17 03:50:44', '2020-11-17 03:41:19', 8, 0, NULL, 0, NULL, NULL, NULL, 3, NULL, 6);

INSERT INTO team
(id, project_id, name, project_url, communication_link)
VALUES(2, 9, 'equipe topzera2', 'github.com', 'slack.com');


INSERT INTO student_team
(id, team_id, student_id, `role`)
VALUES(2, 2, 1, 'Scrum Master');


-- cancelado
INSERT INTO project
(id, teacher_id, meeting_id, title, short_description, complete_description, technology_description, notes, updated_at, created_at, progress, refused, reason, `open`, finished, finished_by, approved_by, created_by, course, semester)
VALUES(10, NULL, NULL, 'projetito cancelado', 'Mattis, netus ante platea sodales sociosqu purus. Aptent potenti quisque natoque penatibus tempus? Platea eu dictumst in dictum dictumst cum donec ligula diam. Et vestibulum fames varius taciti? Turpis fermentum adipiscing metus aptent dapibus facilisis turpis, egestas ornare elit parturient. Gravida scelerisque conubia congue quam natoque lobortis vivamus. Ac dignissim egestas, erat duis primis per. Dui interdum erat integer sagittis torquent nostra cubilia tristique aliquet conubia habitasse. Consequat leo ad lacinia inceptos urna nullam convallis. Suscipit faucibus potenti id rutrum litora sapien. Dictum felis lacus facilisi nec senectus nec?', NULL, NULL, 'Class auctor lectus sollicitudin facilisis maecenas nascetur aptent. Lacus nunc himenaeos gravida volutpat lectus a maecenas felis. Eget venenatis posuere aenean nam mauris vehicula hac himenaeos non nostra congue cursus. Dictumst fringilla habitant facilisi natoque nisi faucibus phasellus potenti v', '2020-11-17 03:42:08', '2020-11-17 03:41:32', 3, 1, NULL, 0, NULL, NULL, NULL, 3, NULL, 0);

