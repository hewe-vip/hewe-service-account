package vip.hewe.service.client.data;

import grpc.service.MemberDataServiceGrpc;
import grpc.service.MemberIdMsg;
import grpc.service.MemberMsg;
import grpc.service.RecordMsg;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class AccountClient {
    private static final Logger logger = LoggerFactory.getLogger(AccountClient.class);

    private final ManagedChannel channel;
    private final MemberDataServiceGrpc.MemberDataServiceBlockingStub blockingStub;

    public AccountClient(String host, int port) {

        this.channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext(true).build();
        this.blockingStub = MemberDataServiceGrpc.newBlockingStub(channel);
    }

    public int createMember(MemberMsg msg) {
        RecordMsg recordMsg = blockingStub.insert(msg);
        int count = recordMsg.getCount();
        return count;
    }

    public MemberMsg select(String id) {
        MemberMsg msg = null;
        try {
            msg = blockingStub.selectById(MemberIdMsg.newBuilder().setId(id).build());
        } catch (StatusRuntimeException sexc) {
            msg = null;
            Status status = sexc.getStatus();
            switch (status.getCode()) {
                case NOT_FOUND:
                    msg = null;
                    break;
                default:
                    sexc.printStackTrace();
                    break;
            }
        } catch (Exception ee) {
            ee.printStackTrace();
        }
        return msg;
    }
}
