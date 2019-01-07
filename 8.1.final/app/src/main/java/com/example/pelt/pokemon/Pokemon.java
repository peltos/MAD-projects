package com.example.pelt.pokemon;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "pokemon")
public class Pokemon implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "pokemonname")
    private String mPokemonName;

    @ColumnInfo(name = "pokemonnumber")
    private int mPokemonNumber;

    @ColumnInfo(name = "pokemonweight")
    private int mPokemonWeight;

    public void setPokemonText(String mPokemonName, int mPokemonNumber, int mPokemonWeight) {
        this.mPokemonName = mPokemonName;
        this.mPokemonNumber = mPokemonNumber;
        this.mPokemonWeight = mPokemonWeight;
    }

    public Pokemon(String mPokemonName, int mPokemonNumber, int mPokemonWeight) {
        this.mPokemonName = mPokemonName;
        this.mPokemonNumber = mPokemonNumber;
        this.mPokemonWeight = mPokemonWeight;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPokemonName() {
        return mPokemonName;
    }

    public void setPokemonName(String mPokemonName) {
        this.mPokemonName = mPokemonName;
    }

    public int getPokemonNumber() {
        return mPokemonNumber;
    }

    public void setPokemonNumber(int mPokemonNumber) {
        this.mPokemonNumber = mPokemonNumber;
    }

    public int getPokemonWeight() {
        return mPokemonWeight;
    }

    public void setPokemonWeight(int mPokemonWeight) {
        this.mPokemonWeight = mPokemonWeight;
    }

    @Override
    public String toString() {
        return "#" +String.format("%03d", mPokemonNumber) + " - " + mPokemonName + " - " + ((double)mPokemonWeight / 10) + "kg";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeInt(this.mPokemonNumber);
        dest.writeString(this.mPokemonName);
        dest.writeInt(this.mPokemonWeight);
    }

    protected Pokemon(Parcel in) {
        this.id = in.readLong();
        this.mPokemonNumber = in.readInt();
        this.mPokemonName = in.readString();
        this.mPokemonWeight = in.readInt();
    }

    public static final Creator<Pokemon> CREATOR = new Creator<Pokemon>() {
        @Override
        public Pokemon createFromParcel(Parcel source) {
            return new Pokemon(source);
        }

        @Override
        public Pokemon[] newArray(int size) {
            return new Pokemon[size];
        }
    };
}