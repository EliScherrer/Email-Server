import java.util.Arrays;

/**
 * Created by Alex Durre on 3/25/2016.
 */
public class DynamicBuffer {
    int initSize;
    int numberMessages;
    Email[] array;
    int arraySize;

    public DynamicBuffer(int initSize) {
        this.initSize = initSize;
        this.numberMessages = 0;
        this.array = new Email[initSize];
        this.arraySize = initSize;
    }
    public int numElements() {
        return this.numberMessages;
    }
    public int getBufferSize() {
        return this.arraySize;
    }

    public void add(Email email) {
        if (this.numberMessages == this.arraySize) {
            sizeUp();
        }
        this.array[this.numberMessages] = email;
        this.numberMessages++;
    }

    public boolean remove(int index) {
        if (index > this.arraySize || index > this.numberMessages - 1) {
            return false;
        }
        if (numberMessages - 1 <= this.arraySize / 4) {
            sizeDown();
        }
        this.array[index] = null;
        //todo maybe i should shift all the elements after i remove one
        this.numberMessages--;
        return true;
    }

    public Email[] getNewest(int n) {
        Email[] temp;
        if (this.numberMessages <= 0) {
            return null;
        }
        else if (n > this.numberMessages) {
            temp = new Email[this.numberMessages];
        }
        else {
            temp = new Email[n];
        }
        for (int i = 0; i < temp.length; i++) {
            if (this.numberMessages - 1 - i >= 0) {
                temp[i] = this.array[this.numberMessages - 1 - i];
            }
        }
        return temp;
    }

    public void sizeUp() {
        this.array = Arrays.copyOf(this.array, this.arraySize * 2);
        this.arraySize = this.arraySize * 2;
    }
    public void sizeDown() {
        this.array = Arrays.copyOf(this.array, this.arraySize / 2);
        this.arraySize = this.arraySize / 2;
    }
}