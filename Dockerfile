ARG APP_INSIGHTS_AGENT_VERSION=3.2.10

# Application image
ARG PLATFORM=""

FROM hmctspublic.azurecr.io/base/java:21-distroless

COPY build/libs/hmc-operational-reports-runner.jar /opt/app/

EXPOSE 4459
CMD [ "hmc-operational-reports-runner.jar" ]
