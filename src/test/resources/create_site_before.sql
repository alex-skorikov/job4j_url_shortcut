delete from links;
delete from sites;

insert into sites(id, site_url, password, status) values
(11, 'site_1', '$2a$08$fNUHI3FnO3cbT6VAcClJOOsIq93f2101ud2RAKiZFAh7Y2h.oFRzC', 'ACTIVE'),
(12, 'site_2', '$2a$08$fNUHI3FnO3cbT6VAcClJOOsIq93f2101ud2RAKiZFAh7Y2h.oFRzC', 'ACTIVE');

insert into links(id, link, code, status, vizit_count, site_id) values
(1, 'link/1', 'IreGCc', 'ACTIVE', 3, 11),
(2, 'link/2', 'X5vumU' , 'ACTIVE', 5, 12);
