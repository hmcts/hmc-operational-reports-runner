ARG APP_INSIGHTS_AGENT_VERSION=3.2.10
FROM hmctspublic.azurecr.io/base/java:17-distroless

COPY build/libs/hmc-operational-reports-runner.jar /opt/app/

EXPOSE 
CMD [ "hmc-operational-reports-runner.jar" ]
