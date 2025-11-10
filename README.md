# Retail Platform
A real-time streaming platform for an online shopping network. This platform will process real- time data from retailer websites, capturing customer interactions, ad engagements, etc. The processed data will be utilized to generate insights via APIs, enabling end users to optimize their advertising campaigns &amp; enhance user engagement.

### Code Flow Diagram:
![alt text](https://github.com/AishwaryaMahindru/retail-platform/blob/main/System%20Design/CodeFlowDiagram/Retail%20Platform%20Flow%20Diagram.png)


### Note:
For the purpose of this Proof of Concept (POC), the APIs of the Metrics Service, Ad Service, Authorization Service, and User & Tenant Service have been consolidated into a single service module.

This integration simplifies the setup and demonstrates the end-to-end data flow — from event ingestion to metric computation and user validation — within one deployable unit.

In a production-ready architecture, these would be separate microservices with their own databases, APIs, and deployment pipelines.


## Technology Stack (For running this POC)
- **Backend:** Java 21, Spring Boot, Gradle
- **Storage:** Cassandra

## Multi-Tenancy Design
- Every tenant (retailer) has a unique **tenantId** in the request headers specified by X-Tenant-Id
- All microservices validate tenant context.

## Local Setup

### Prerequisites
- **Java 21+**
- **Gradle 8+**
- **Docker Desktop** (for running Cassandra)
- **Git**

### Steps
```bash
# 1. Git Clone
git clone https://github.com/AishwaryaMahindru/retail-platform.git
cd retail-platform

# 2. Start dependencies
docker-compose up -d

# 3. Build the project
./gradlew clean build --refresh-dependencies

# 4. Run the microservices 
./gradlew :retail-data-stream:bootRun
```

### API Execution
Please refer APIs in Postman Collection at path: 
**postman-collection/retail-platform.postman_collection.json**

| **#** | **API Name**                 | **Method** | **Endpoint**                                                              | **Headers**                                                  | **Auth**     | **Example Parameters / Body**                                                                                                                          | **Description**                                                                                |
| :---: | :--------------------------- | :--------- | :------------------------------------------------------------------------ | :----------------------------------------------------------- | :----------- |:-------------------------------------------------------------------------------------------------------------------------------------------------------| :--------------------------------------------------------------------------------------------- |
|   1   | **Generate Token**           | `POST`     | `http://localhost:8080/api/v1/auth/login?username=john&password=admin123` | `Content-Type: application/json`                             | No Auth      | **Query Params:** `username=john` `password=admin123`                                                                                                  | Generates a JWT token for user authentication. Used by all protected endpoints.                |
|   2   | **Create Retail Metrics**    | `POST`     | `http://localhost:8080/api/v1/ad/CAMPAIGN_1001/clickx`                    | `Content-Type: application/json``X-Tenant-Id: retailerA` | Bearer Token | **Body (JSON):**`json{  "eventType": "add_to_cart",  "productId": "P123",  "metricValue": 5.0}`                                                        | Creates or simulates a metric event for a specific campaign (e.g., ad click or product view).  |
|   3   | **Get Retail Metrics**       | `GET`      | `http://localhost:8080/api/v1/metrics`                                    | `Content-Type: application/json``X-Tenant-Id: retailerA` | Bearer Token | **Optional Query Params:** `tenantId=retailerA` `productId=P123`                                                                                       | Retrieves aggregated metrics (views, clicks, conversions) across tenants or specific products. |
|   4   | **Ad Click Count Increment** | `POST`     | `http://localhost:8080/api/v1/ad/CAMPAIGN_1001/click`                     | `Content-Type: application/json``X-Tenant-Id: retailerA` | Bearer Token | **Path Variable:** `campaignId=CAMPAIGN_1001`                                                                                                          | Increments ad click count for the given campaign and tenant.
|   5   | **Get Ad Click Count**       | `GET`      | `http://localhost:8080/api/v1/ad/CAMPAIGN_1001/clicks`                    | `Content-Type: application/json``X-Tenant-Id: retailerA` | Bearer Token | **Path Variable:** `campaignId=CAMPAIGN_1001`                                                                                                          | Returns total click count and related ad engagement statistics for a specific campaign.        |

