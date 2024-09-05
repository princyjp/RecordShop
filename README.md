# RecordShop
Welcome to the Northcoders Record Shop, a super-store for all your music needs! From Abba to Zappa, you want it? WE GOT IT!
Or at least I think we do...our inventory and stock system is quite antiquated and relies on writing down any new stock and sales we make by hand on paper. Sometimes, if we’re busy, we often forget to update these records, so who knows what's actually in stock!

It’s time we update this and drag ourselves kicking and screaming into the 21st century with a computerised inventory system. We’d also like our current stock to be available to purchase online, so keeping tabs on what we have in stock is going to be essential.

**Tasks**
To  design and build the backend for the record shop inventory system which will allow the Northcoders Record Shop to:
1. Store information about the records they have in stock.
2. Query this data in various ways.
3. Update it accordingly.

**Project Criteria**
Create API endpoints with the appropriate HTTP verbs.
API base URL and endpoints must be appropriately named.
Make sure your API is production-quality code - good separation of concerns, clean, well-tested and readable.
Write a descriptive README to document the key features of your solution, your assumptions, approaches and future thoughts.
All projects should include a /health endpoint to give the health of the application (perhaps look into the Spring Boot Actuator dependency for this)

**User Stories**
For the Northcoders Record Shop inventory system, I want the backend API endpoints to be able to return and interact with the data in the following way:
1. list all albums in stock
2. get album by id
3. list all albums by a given artist
4. list all albums by a given release year
5. list all albums by a given genre
6. get album information by album name
7. add new albums into the database
8. update album details
9. delete albums from the database

**For example:**

GIVEN a customer wants to check the stock to purchase a particular album
WHEN they enter details of the album name
THEN the information regarding this album and number of albums in stock is returned so a transaction can take place
AND the number of this album in stock is updated/deleted if purchased
