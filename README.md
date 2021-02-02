# IT EVENT API

## DESCRIPTION:

Created API on SDA project group.

[more info](https:/#)

## END-P0INTS

[SWAGGER2](https://it-event-app.herokuapp.com/v2/api-docs) 

[SWAGGER2 UI](https://it-event-app.herokuapp.com/swagger-ui.html) 

## AUTHORIZATION

**permit all:** 
* /auth/signin 
* /api/user/ [only POST]
* /api/** [only GET]

**authenticated:**
* /me

**admin:**
* /api/** [CRUD]

**moderator:**
* /api/** [CRU]