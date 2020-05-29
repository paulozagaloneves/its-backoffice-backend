-- CRIAÇÃO USUÁRIO ADMIN
    
insert 
into
    user
    (creation, creation_username, last_updated, last_updated_username, status, password, username) 
values
    (CURRENT_TIMESTAMP(), 'admin', CURRENT_TIMESTAMP(), 'admin', true, 'admin', 'admin');


    
    





