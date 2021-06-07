import java.util.*;

public class LineOfSight {
    public static void main(String[] args) {
        int x1 = 784, y1 = 260, x2 = 112, y2 = 260;
        //System.out.println(drawLine(x1, y1, x2, y2));
        System.out.println();
        doesCollide(drawLine(x1, y1, x2, y2));
        System.out.println(Level.getLevel1MidCords()[0]);
    }

    public static ArrayList<String> drawLine(int x1, int y1, int x2, int y2) {
        // delta of exact value and rounded value of the dependent variable
        ArrayList<String> cords = new ArrayList<String>();
        int d = 0;
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int dx2 = 2 * dx; // slope scaling factors to
        int dy2 = 2 * dy; // avoid floating point
        int ix = x1 < x2 ? 1 : -1; // increment direction
        int iy = y1 < y2 ? 1 : -1;
        int x = x1;
        int y = y1;

        if (dx >= dy) {
            while (true) {
                cords.add(x + " " + y);
                if (x == x2)
                    break;
                x += ix;
                d += dy2;
                if (d > dx) {
                    y += iy;
                    d -= dx2;
                }
            }
        } else {
            while (true) {
                cords.add(x + " " + y);
                if (y == y2)
                    break;
                y += iy;
                d += dx2;
                if (d > dy) {
                    x += ix;
                    d -= dy2;
                }
            }
        }
        return cords;
    }

    public static boolean doesCollide(ArrayList<String> cords) {
        for (String s : cords) {
            for (int i = 0; i < 16; i++) {
                if (s.equals(Level.getLevel1Cords()[i])) {
                    return true;
                }
            }
        }
        return false;
    }
}

/*
 * [386 484, 387 483, 388 483, 389 482, 390 482, 391 481, 392 481, 393 480, 394
 * 480, 395 479, 396 479, 397 478, 398 478, 399 477, 400 477, 401 476, 402 476,
 * 403 475, 404 475, 405 474, 406 474, 407 473, 408 473, 409 472, 410 472, 411
 * 471, 412 471, 413 470, 414 470, 415 469, 416 469, 417 468, 418 468, 419 467,
 * 420 467, 421 466, 422 466, 423 465, 424 465, 425 464, 426 464, 427 463, 428
 * 463, 429 462, 430 462, 431 461, 432 461, 433 460, 434 460, 435 459, 436 459,
 * 437 458, 438 458, 439 457, 440 457, 441 456, 442 455, 443 455, 444 454, 445
 * 454, 446 453, 447 453, 448 452, 449 452, 450 451, 451 451, 452 450, 453 450,
 * 454 449, 455 449, 456 448, 457 448, 458 447, 459 447, 460 446, 461 446, 462
 * 445, 463 445, 464 444, 465 444, 466 443, 467 443, 468 442, 469 442, 470 441,
 * 471 441, 472 440, 473 440, 474 439, 475 439, 476 438, 477 438, 478 437, 479
 * 437, 480 436, 481 436, 482 435, 483 435, 484 434, 485 434, 486 433, 487 433,
 * 488 432, 489 432, 490 431, 491 431, 492 430, 493 430, 494 429, 495 429, 496
 * 428, 497 427, 498 427, 499 426, 500 426, 501 425, 502 425, 503 424, 504 424,
 * 505 423, 506 423, 507 422, 508 422, 509 421, 510 421, 511 420, 512 420, 513
 * 419, 514 419, 515 418, 516 418, 517 417, 518 417, 519 416, 520 416, 521 415,
 * 522 415, 523 414, 524 414, 525 413, 526 413, 527 412, 528 412, 529 411, 530
 * 411, 531 410, 532 410, 533 409, 534 409, 535 408, 536 408, 537 407, 538 407,
 * 539 406, 540 406, 541 405, 542 405, 543 404, 544 404, 545 403, 546 403, 547
 * 402, 548 402, 549 401, 550 401, 551 400, 552 399, 553 399, 554 398, 555 398,
 * 556 397, 557 397, 558 396, 559 396, 560 395, 561 395, 562 394, 563 394, 564
 * 393, 565 393, 566 392, 567 392, 568 391, 569 391, 570 390, 571 390, 572 389,
 * 573 389, 574 388, 575 388, 576 387, 577 387, 578 386, 579 386, 580 385, 581
 * 385, 582 384, 583 384, 584 383, 585 383, 586 382, 587 382, 588 381, 589 381,
 * 590 380, 591 380, 592 379, 593 379, 594 378, 595 378, 596 377, 597 377, 598
 * 376, 599 376, 600 375, 601 375, 602 374, 603 374, 604 373, 605 373, 606 372,
 * 607 371, 608 371, 609 370, 610 370, 611 369, 612 369, 613 368, 614 368, 615
 * 367, 616 367, 617 366, 618 366, 619 365, 620 365, 621 364, 622 364, 623 363,
 * 624 363, 625 362, 626 362, 627 361, 628 361, 629 360, 630 360, 631 359, 632
 * 359, 633 358, 634 358, 63 658 346, 659 345, 660 345, 661 344, 662 343, 663
 * 343, 664 342, 665 342, 666 341, 667 341, 668 340, 669 340, 670 339, 671 339,
 * 672 338, 673 338, 674 337, 675 337, 676 336, 677 336, 678 335, 679 335, 680
 * 334, 681 334, 682 333, 683 333, 684 332, 685 332, 686 331, 687 331, 688 330,
 * 689 330, 690 329, 691 329, 692 328, 693 328, 694 327, 695 327, 696 326, 697
 * 326, 698 325, 699 325, 700 324, 701 324, 702 323, 703 323, 704 322, 705 322,
 * 706 321, 707 321, 708 320, 709 320, 710 319, 711 319, 712 318, 713 318, 714
 * 317, 715 317, 716 316, 717 315, 718 315, 719 314, 720 314, 721 313, 722 313,
 * 723 312, 724 312, 725 311,00, 749 299, 750 299, 751 298, 752 298, 753 297,
 * 754 297, 755 296, 756 296, 757 295, 758 295, 759 294, 760 294, 761 293, 762
 * 293, 763 292, 764 292, 765 291, 766 291, 767 290, 768 290, 769 289, 770 289,
 * 771 288, 772 287, 773 287, 774 286, 775 286, 776 285, 777 285, 778 284, 779
 * 284, 780 283, 781 283, 782 282, 783 282, 784 281, 785 281, 786 280, 787 280,
 * 788 279, 789 279, 790 278, 791 278, 792 277, 793 277, 794 276, 795 276, 796
 * 275, 797 275, 798 274, 799 274, 800 273, 801 273, 802 272, 803 272, 804 271,
 * 805 271, 806 270, 807 270, 808 269, 809 269, 810 268, 811 268, 812 267, 813
 * 267, 814 266, 815 266, 816 265, 817 265, 818 264, 819 264, 820 263, 821 263,
 * 822 262, 823 262, 824 261, 825 261, 826 260]
 */