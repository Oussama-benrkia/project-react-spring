package ma.benouss.task.dto.util;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChangeStatusResponse<Res> {
    private Res response;
    private Boolean changed;

    public ChangeStatusResponse(Res response, Boolean changed) {
        this.response = response;
        this.changed = changed;
    }

    public ChangeStatusResponse() {
    }

    public static <Res> ChangeStatusResponse<Res> buildResponse(Res response, Boolean changed) {
        return ChangeStatusResponse.<Res>builder()
                .response(response)
                .changed(changed)
                .build();
    }

    public static <Res> ChangeStatusResponseBuilder<Res> builder() {
        return new ChangeStatusResponseBuilder<Res>();
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ChangeStatusResponse)) return false;
        final ChangeStatusResponse<?> other = (ChangeStatusResponse<?>) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$response = this.getResponse();
        final Object other$response = other.getResponse();
        if (this$response == null ? other$response != null : !this$response.equals(other$response)) return false;
        final Object this$changed = this.getChanged();
        final Object other$changed = other.getChanged();
        if (this$changed == null ? other$changed != null : !this$changed.equals(other$changed)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ChangeStatusResponse;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $response = this.getResponse();
        result = result * PRIME + ($response == null ? 43 : $response.hashCode());
        final Object $changed = this.getChanged();
        result = result * PRIME + ($changed == null ? 43 : $changed.hashCode());
        return result;
    }

    public String toString() {
        return "ChangeStatusResponse(response=" + this.getResponse() + ", changed=" + this.getChanged() + ")";
    }

    public static class ChangeStatusResponseBuilder<Res> {
        private Res response;
        private Boolean changed;

        ChangeStatusResponseBuilder() {
        }

        public ChangeStatusResponseBuilder<Res> response(Res response) {
            this.response = response;
            return this;
        }

        public ChangeStatusResponseBuilder<Res> changed(Boolean changed) {
            this.changed = changed;
            return this;
        }

        public ChangeStatusResponse<Res> build() {
            return new ChangeStatusResponse<Res>(this.response, this.changed);
        }

        public String toString() {
            return "ChangeStatusResponse.ChangeStatusResponseBuilder(response=" + this.response + ", changed=" + this.changed + ")";
        }
    }
}
