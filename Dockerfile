ARG APP_INSIGHTS_AGENT_VERSION=3.6.2

# Application image
ARG PLATFORM=""

FROM hmctspublic.azurecr.io/base/java:21-distroless

COPY lib/applicationinsights.json /opt/app/
COPY build/libs/hmc-operational-reports-runner.jar /opt/app/

EXPOSE 4459
CMD [ "hmc-operational-reports-runner.jar" ]
