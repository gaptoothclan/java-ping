This project is a web service, you can post a customer in as JSON, there are integrations configured in a database instance, of where to send the customer. Each integration is a web service, the customer details are formatted and sent to each until an accept has been detected. 

The response is then sent back as JSON.

  {
    "address": {
      "addressLine1": "House Name",
      "addressLine2": "12 South Street",
      "addressLine3": "Winton",
      "town": "Bournemouth",
      "county": "Dorset",
      "postCode": "BH11HB"
    },
    "customer": {
      "title": "Mr",
      "firstName": "Charles",
      "lastName": "Bryant",
      "email": "charles@test.com",
      "dob": "23/07/1982",
      "homePhone": "01202000000",
      "mobilePhone": "07000000000",
      "employmentStatus": "Employed",
      "employmentStartDate": "05/07/2012",
      "salaryAmount": 12000
    },
    "requestConfig": {
      "responseFormat": "json",
      "apiKey": "xxxx-xxxx-xxxx-xxxx"
    }
  }
