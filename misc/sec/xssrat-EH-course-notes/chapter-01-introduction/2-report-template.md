**Title**
Hidden endpoint exposes client details to all users

**Type of vulnerability**
Broken Access Control

**Endpoint**
https://UncleRatsCheeseShop.com/api/v1/getClients

**Description**
Users can only view the details of their own clients as intended, however, when they make a GET call to /api/v1/getClients, the user can view all the clients in the system.
This endpoint is normally not available in the UI but we can trigger a call to /api/v2/getClients by clicking the by clicking the "View clients" button.
The call to this endpoint shows the user’s own clients as intended.

**Steps to reproduce**
_Requirements_

1. MiTM proxy such as burp
2. User with "Salesmen" privileges
   2a. Salesman privileges can only view their own clients

_Steps_

1. Log in to the application
2. Navigate to clients module
   2a. Make sure you have the MiTM proxy enabled
3. Click the "View clients" button
4. In the MiTM proxy, click the "history" tab and find the call to /api/v2/getClients
5. Send the request to the repeater and replace the V2 with V1
   5a. /api/v2/getClients >>> /api/v1/getClients
6. Resend the request

_Actual result_

1. "Salesmen" User can view all clients, even those that are not his

_Expected result_

1. "Salesmen" User can only view their own clients

**Impact**
attacker can view all the clients, even those that do not belong to him

**Remediation**
Disable the old API endpoint

**Severity**
High

- This report is written in markdown to make it easier to copy. Most bug bounty platforms work in markdown.  
  The title is important, we need to describe the issue we found briefly. Remember that your issue might have to get talked about in meetings. This means you need to make it very clear and sometimes even to non-technical people. Example of a bad title: “When i was surfing the website, i found like this ID that i changed from 9 to 10 and suddenly i saw Josh’s grades… oh my gosh!!” ……. Better title: “IDOR in grades functionality”
- The type of issue matters and it can be more than 1 issue type. For example, an IDOR is also BAC. Note down every issue type that fits what you found but be fair and doesn’t label an HTML injection as XSS if you can not trigger javascript.
  The endpoint is usually clear. If an issue exists of a chain of endpoints, it never hurts to note them all down.
- The description is very important. Besides the steps to reproduce we need to describe what is happening in a few sentences so the issue can be talked about easily in meetings.
  Your steps to reproduce should always include some pre-requisites. This is what you need to do to set up the scenario in which the defect occurs. It can include things like setting up accounts, objects, or tools
- In your steps to reproduce the problem, you need to be very clear and try to be as non-technical as you can be while also being as detailed as you can be. It is better to write down too much than too little details because the easier something is to reproduce, the easier it gets triaged.
  The expected result might sometimes be hard to guess but you can refer to the manual or documentation or take an educated guess.
- The actual result or impact is one of the most important pieces of information. Not everyone will want to reproduce the issue to know what it is about and they will judge the severity of the issue based on the results and the difficulty for an attacker to reproduce it.
- I usually try to advise the developers in fixing the issue as I have a thorough understanding of the application I’ve been working on. This is always well received.
  Be fair when judging severity. If you judge too low, your triager should upgrade the severity for you. My experience with this is that it is better to select a lower impact, even if it sometimes loses you thousands of dollars. Also, judge the severity based on your target. For example, the login system of a bank matters a lot more than the login system of your local bakery.
