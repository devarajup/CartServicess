package com.cjss.CartService.util;

public enum OrderStatus {
    RECEIVED {
        @Override
        public String toString() {
            return "RECEIVED";
        }
    },
    PROCESSING {
        @Override
        public String toString() {
            return "PROCESSING";
        }
    },
    PACKING {
        @Override
        public String toString() {
            return "PACKING";
        }
    },
    SHIPPING {
        @Override
        public String toString() {
            return "SHIPPING";
        }
    },
    DELIVERED {
        @Override
        public String toString() {
            return "DELIVERED";
        }
    },
    RETURNED {
        @Override
        public String toString() {
            return "RETURNED";
        }
    }
    }
