package ddd.domain;

import lombok.Getter;

import java.time.Instant;

@Getter
public abstract class Entity {
    private final Id id;

    private final Instant _createdAt;
    private final String _createdBy;
    private Instant _updatedAt;
    private String _updatedBy;
    private Integer _version;

    private Boolean __hasBeenModified;

    protected Entity(Id id, Instant createdAt, String createdBy, Instant updatedAt, String updatedBy) {
        this.id = id;
        this._createdAt = createdAt;
        this._createdBy = createdBy;
        this._updatedAt = updatedAt;
        this._updatedBy = updatedBy;
    }

    public Id id() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return this.id.equals(entity.id);
    }

    private void markAsModified(String userId) {
        this._updatedBy = userId;
        this._updatedAt = Instant.now();
        this._version += !this.__hasBeenModified ? 1 : 0;
        this.__hasBeenModified = true;
    }

    private void markAsPersisted() {
        this.__hasBeenModified = false;
    }
}
